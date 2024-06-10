import {Component, Input, OnDestroy, OnInit} from '@angular/core';
import {Subject, takeUntil} from "rxjs";
import {ActivatedRoute, Router} from "@angular/router";
import {ServicesService} from "../../services.service";
import {MessageService, PrimeTemplate} from "primeng/api";
import {DataViewModule} from "primeng/dataview";
import {ImageModule} from "primeng/image";
import {NgClass, NgForOf} from "@angular/common";
import {IComment, IFormComment, IFormPost} from "../../models.model";
import {format} from "date-fns";
import {Button} from "primeng/button";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DialogModule} from "primeng/dialog";
import {ChipsModule} from "primeng/chips";

@Component({
  selector: 'app-comments',
  standalone: true,
  imports: [
    DataViewModule,
    ImageModule,
    NgForOf,
    PrimeTemplate,
    NgClass,
    Button,
    DialogModule,
    ReactiveFormsModule,
    ChipsModule
  ],
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.scss'
})
export class CommentsComponent implements OnInit, OnDestroy{
  postId: string = "";
  visible: boolean = false;
  comments: IComment[] = []
  submit: boolean = false;
  form: FormGroup;
  destroy$ = new Subject();
  constructor(private formBuilder: FormBuilder,private route: ActivatedRoute, private servicesService:ServicesService,
              private messageService: MessageService) {
    this.form= this.formBuilder.nonNullable.group({
      id:[],
      fullName: ["", Validators.required],
      content: ["", Validators.required],
      postId: [""],
      createdAt: [""],
      updatedAt: [""],
    });
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      takeUntil(this.destroy$)
    ).subscribe((params) => {
      const id = params.get('id') || '';
      if(id) {
        this.postId = id;
        this.getCommentsByPost(this.postId);
      }
    });
  }

  getCommentsByPost(id: string) {
    this.servicesService.getCommentsByPost(id).pipe(takeUntil(this.destroy$)).subscribe((value)=> {
     this.comments = value.map(item=>
       ({...item, createdAt: format(new Date(item.createdAt), "yyyy/MM/dd HH:mm")}))
    })
  }

  openForm(comment?: IComment) {
    if(comment) {
      this.form.controls["id"].setValue(comment.id);
      this.form.controls["fullName"].setValue(comment.fullName);
      this.form.controls["content"].setValue(comment.content);
    }
    this.visible = true;
  }

  hiddenForm() {
    this.visible = false;
    this.form.reset();
  }

  onSubmit() {
    this.submit = true;
    if(this.form.invalid) return;
    const body = this.form.value;
    body.postId = Number(this.postId);
    body.id ? this.updateComment(body) : this.createComment(body);
  }

  createComment(body: IFormComment) {
    this.servicesService.createComment(body).pipe(takeUntil(this.destroy$)).subscribe((id)=> {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Create comment success!' });
      this.getCommentsByPost(this.postId);
      this.hiddenForm();
    })
  }

  updateComment(body: IFormComment) {
    this.servicesService.updateComment(body).pipe(takeUntil(this.destroy$)).subscribe((id)=> {
      this.messageService.add({ severity: 'success', summary: 'Success', detail: 'Update comment success!' });
      this.getCommentsByPost(this.postId);
      this.hiddenForm();
    })
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
