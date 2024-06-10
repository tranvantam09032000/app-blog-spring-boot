import {Component, OnDestroy, OnInit} from '@angular/core';
import {Subject, combineLatest, takeUntil} from "rxjs";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ServicesService} from "../../services.service";
import {MessageService} from "primeng/api";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {IAuthor, ICategory, IFormPost, IPost, ITag} from "../../models.model";
import {} from "rxjs/internal/operators/combineLatest";
import {InputTextModule} from "primeng/inputtext";
import {DropdownModule} from "primeng/dropdown";
import {MultiSelectModule} from "primeng/multiselect";
import {Button} from "primeng/button";
import {EditorModule} from "primeng/editor";
import {InputSwitchModule} from "primeng/inputswitch";
import {NgClass} from "@angular/common";

@Component({
  selector: 'app-form-post',
  standalone: true,
  imports: [
    ReactiveFormsModule,
    InputTextModule,
    DropdownModule,
    MultiSelectModule,
    Button,
    RouterLink,
    EditorModule,
    InputSwitchModule,
    NgClass
  ],
  templateUrl: './form-post.component.html',
  styleUrl: './form-post.component.scss'
})
export class FormPostComponent implements OnInit, OnDestroy{
  authors: IAuthor[] = [];
  categories: ICategory[]= [];
  tags: ITag[] = [];
  form: FormGroup;
  submit = false;
  destroy$ = new Subject();
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute, private servicesService:ServicesService,
              private router: Router, private messageService: MessageService) {
    this.form= this.formBuilder.group({
      title: ["", Validators.required],
      id:[],
      subTitle: ["", Validators.required],
      content: ["", Validators.required],
      thumbnail: ["", Validators.required],
      createdAt: [""],
      updatedAt: [""],
      published: [true],
      tags:[[], Validators.required],
      authorId: ["",Validators.required],
      categoryId: ["", Validators.required],
    });
  }

  get f() {
    return this.form.controls;
  }
  ngOnInit() {
    this.route.paramMap.pipe(
      takeUntil(this.destroy$)
    ).subscribe((params) => {
      const id = params.get('id') || '';
      if(id) {
        this.getPostById(id);
      }
    });
    combineLatest(
      this.servicesService.getAuthors(),
      this.servicesService.getTags(),
      this.servicesService.getCategories()
    ).pipe(takeUntil(this.destroy$)).subscribe(
      ([authors, tags, categories])=> {
        this.authors =authors;
        this.tags = tags;
        this.categories = categories;
      }
    )
  }

  getPostById(id: string) {
    this.servicesService.getPostById(id).pipe(takeUntil(this.destroy$)).subscribe(
      {
        next: (value)=> {
          this.form.controls["id"].setValue(value.id);
          this.form.controls["title"].setValue(value.title);
          this.form.controls["subTitle"].setValue(value.subTitle);
          this.form.controls["content"].setValue(value.content);
          this.form.controls["thumbnail"].setValue(value.thumbnail);
          this.form.controls["published"].setValue(value.published);
          this.form.controls["authorId"].setValue(value.author.id);
          this.form.controls["categoryId"].setValue(value.category.id);
          this.form.controls["tags"].setValue(value.tags.map(item=> item.id));
        },
        error: (error)=> {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Post not found' });

          this.router.navigateByUrl("/home");
        }
      }
    )
  }

  onSubmit() {
    this.submit = true;
    if(this.form.invalid) return;
    const body = this.form.value;
    body.authorId = Number(body.authorId);
    body.categoryId = Number(body.categoryId);
    body.tags = body.tags.map((item: string)=> Number(item))
   body.id ? this.updatePost(body) : this.createPost(body);
  }

  createPost(body: IFormPost) {
    this.servicesService.createPost(body).pipe(takeUntil(this.destroy$)).subscribe((id)=> {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Create post success!' });
      this.router.navigateByUrl(`/post/view/${id}`);
    })
  }

  updatePost(body: IFormPost) {
    this.servicesService.updatePost(body).pipe(takeUntil(this.destroy$)).subscribe((id)=> {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Update post success!' });
      this.router.navigateByUrl(`/post/view/${id}`);
    })
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
