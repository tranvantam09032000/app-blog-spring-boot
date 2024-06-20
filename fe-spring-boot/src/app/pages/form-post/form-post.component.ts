import {Component, OnDestroy, OnInit, ViewChild} from '@angular/core';
import {Subject, combineLatest, takeUntil} from "rxjs";
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {ServicesService} from "../../services.service";
import {MessageService} from "primeng/api";
import {FormArray, FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {IAuthor, ICategory, IContent, IFormPost, IPost, ITag} from "../../models.model";
import {} from "rxjs/internal/operators/combineLatest";
import {InputTextModule} from "primeng/inputtext";
import {DropdownModule} from "primeng/dropdown";
import {MultiSelectModule} from "primeng/multiselect";
import {Button} from "primeng/button";
import {EditorModule} from "primeng/editor";
import {InputSwitchModule} from "primeng/inputswitch";
import {NgClass} from "@angular/common";
import {StepperModule} from "primeng/stepper";
import {AvatarModule} from "primeng/avatar";
import {FloatLabelModule} from "primeng/floatlabel";
import {GalleriaModule} from "primeng/galleria";

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
    NgClass,
    StepperModule,
    AvatarModule,
    FloatLabelModule,
    GalleriaModule
  ],
  templateUrl: './form-post.component.html',
  styleUrl: './form-post.component.scss'
})
export class FormPostComponent implements OnInit, OnDestroy {
  @ViewChild("stepper") stepper?: any;
  authors: IAuthor[] = [];
  categories: ICategory[] = [];
  tags: ITag[] = [];
  contentTypes: Record<string, string>[] = [
    {name: "Text", value: "text"},
    {name: "Image", value: "image"},
    {name: "Slider", value: "slider"},
  ];
  form: FormGroup = this.formBuilder.group({
    title: ["", Validators.required],
    id: [],
    subTitle: ["", Validators.required],
    contents: this.formBuilder.array<FormGroup>([]),
    thumbnail: ["", Validators.required],
    createdAt: [""],
    updatedAt: [""],
    published: [true],
    tags: [[], Validators.required],
    authorId: ["", Validators.required],
    categoryId: ["", Validators.required],
  });
  submit = false;
  isShow: boolean = false;
  activeStep: number = -1;
  destroy$ = new Subject();

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private servicesService: ServicesService,
              private router: Router, private messageService: MessageService) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      takeUntil(this.destroy$)
    ).subscribe((params) => {
      const id = params.get('id') || '';
      if (id) {
        this.getPostById(id);
      } else {
        this.addContent();
      }
    });
    combineLatest(
      this.servicesService.getAuthors(),
      this.servicesService.getTags(),
      this.servicesService.getCategories()
    ).pipe(takeUntil(this.destroy$)).subscribe(
      ([authors, tags, categories]) => {
        this.authors = authors;
        this.tags = tags;
        this.categories = categories;
      }
    )
  }

  get f() {
    return this.form.controls;
  }

  get contents(): FormArray {
    return this.form.controls["contents"] as FormArray<FormGroup>
  }

  setContents(values: IContent[], id: number) {
    values.forEach((item) => {
      if (item.type === "slider") {
        const content = {...item, value: item.value.toString().split(','), postId: Number(id)};
        this.addContent(content);
      } else {
        const content = {...item, postId: Number(id)};
        this.addContent(content);
      }
    });
  }

  addContent(value?: IContent) {
    this.isShow = false;
    const content = this.formBuilder.group({
      id: [value?.id || ''],
      type: [value?.type || '', Validators.required],
      value: [value?.value || '', Validators.required],
      position: [value?.position || '', Validators.required],
      postId: [value?.postId || '']
    });
    this.contents.push(content);
    setTimeout(() => {
      this.isShow = true;
      this.activeStep++;
    })
  }

  deleteContent(index: number) {
    this.isShow = false;
    this.contents.removeAt(index);
    setTimeout(() => {
      this.isShow = true;
      this.activeStep = index - 1;
    })
  }

  changeType(value: string, index: number) {
    this.contents.controls[index].get("value")?.setValue(value === "slider" ? [""] : "");
    this.contents.controls[index].get("position")?.setValue(index);
  }

  addImageSlider(index: number) {
    const images = this.contents.controls[index].get("value")?.value;
    images.push("");
    this.contents.controls[index].get("value")?.setValue(images);
  }

  updateValueSlider(index: number, itemIndex: number, value: any) {
    const images = this.contents.controls[index].get("value")?.value || [];
    images[itemIndex] = value.target.value;
    this.contents.controls[index].get("value")?.setValue(images);
  }

  removeImageSlider(index: number, itemIndex: number) {
    const images = this.contents.controls[index]?.get("value")?.value;
    images.splice(itemIndex, 1);
    this.contents.controls[index].get("value")?.setValue(images);
  }

  getPostById(id: string) {
    this.servicesService.getPostById(id).pipe(takeUntil(this.destroy$)).subscribe(
      {
        next: (value) => {
          this.form.controls["id"].setValue(value.id);
          this.form.controls["title"].setValue(value.title);
          this.form.controls["subTitle"].setValue(value.subTitle);
          this.form.controls["thumbnail"].setValue(value.thumbnail);
          this.form.controls["published"].setValue(value.published);
          this.form.controls["authorId"].setValue(value.author.id);
          this.form.controls["categoryId"].setValue(value.category.id);
          this.form.controls["tags"].setValue(value.tags.map(item => item.id));
          this.setContents(value.contents, value.id)
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Post not found'});

          this.router.navigateByUrl("/home");
        }
      }
    )
  }

  onSubmit() {
    this.submit = true;
    if (this.form.invalid) return;
    const body = this.form.value;
    body.contents = body.contents.map((item: any) => {
      if (item.type === "slider") {
        return {...item, value: item.value.toString()}
      } else {
        return item;
      }
    })
    body.authorId = Number(body.authorId);
    body.categoryId = Number(body.categoryId);
    body.tags = body.tags.map((item: string) => Number(item))
    body.id ? this.updatePost(body) : this.createPost(body);
  }

  createPost(body: IFormPost) {
    this.servicesService.createPost(body).pipe(takeUntil(this.destroy$)).subscribe((id) => {
      this.messageService.add({severity: 'success', summary: 'Success', detail: 'Create post success!'});
      this.router.navigateByUrl(`/post/view/${id}`);
    })
  }

  updatePost(body: IFormPost) {
    this.servicesService.updatePost(body).pipe(takeUntil(this.destroy$)).subscribe((id) => {
      this.messageService.add({severity: 'success', summary: 'Success', detail: 'Update post success!'});
      this.router.navigateByUrl(`/post/view/${id}`);
    })
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
