import {Component, OnDestroy, OnInit} from '@angular/core';
import {map, Observable, Subject, takeUntil} from "rxjs";
import {ActivatedRoute} from "@angular/router";
import {MessageService, PrimeTemplate} from "primeng/api";
import {DataViewModule} from "primeng/dataview";
import {ImageModule} from "primeng/image";
import {AsyncPipe, DatePipe, NgClass, NgForOf} from "@angular/common";
import {Button} from "primeng/button";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {DialogModule} from "primeng/dialog";
import {ChipsModule} from "primeng/chips";
import {CommentService} from "../../services/comment.service";
import {IComment, IFormComment} from "../../models/comment.model";
import {CardModule} from "primeng/card";
import {InputTextareaModule} from "primeng/inputtextarea";

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
    ChipsModule,
    CardModule,
    InputTextareaModule,
    AsyncPipe,
    DatePipe
  ],
  templateUrl: './comments.component.html',
  styleUrl: './comments.component.scss'
})
export class CommentsComponent implements OnInit, OnDestroy {
  commentsObs$: Observable<any> = new Observable();
  postId: string = "";
  visibleForm: boolean = false;
  comments: IComment[] = []
  submit: boolean = false;
  form: FormGroup;
  destroy$ = new Subject();

  constructor(private formBuilder: FormBuilder, private route: ActivatedRoute, private commentService: CommentService,
              private messageService: MessageService) {
    this.form = this.formBuilder.nonNullable.group({
      id: [],
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
      if (id) {
        this.postId = id;
        this.fetchCommentsByPost(this.postId);
      }
    });
  }

  openForm(comment?: IComment) {
    if (comment) {
      this.form.controls["id"].setValue(comment.id);
      this.form.controls["fullName"].setValue(comment.fullName);
      this.form.controls["content"].setValue(comment.content);
    }
    this.visibleForm = true;
  }

  hiddenForm() {
    this.visibleForm = false;
    this.submit = false;
    this.form.reset();
  }

  onSubmit() {
    this.submit = true;
    if (this.form.invalid) return;
    const body = this.form.value;
    body.postId = Number(this.postId);
     body.id ? this.updateComment(body) : this.createComment(body);
  }

  fetchCommentsByPost(id: string) {
    this.commentsObs$ = this.commentService.getCommentsByPost(id).pipe(
      map((values)=> {
        this.comments = values;
        return values;
      })
    )
  }

  createComment(body: IFormComment) {
    this.commentsObs$ = this.commentService.createComment(body).pipe(
      map(()=> {
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Create comment success!'});
        this.fetchCommentsByPost(this.postId);
        this.hiddenForm();
      })
    )
  }

  updateComment(body: IFormComment) {
    this.commentsObs$ = this.commentService.updateComment(body).pipe(
      map(()=> {
        this.messageService.add({severity: 'success', summary: 'Success', detail: 'Update comment success!'});
        this.fetchCommentsByPost(this.postId);
        this.hiddenForm();
      })
    )
  }

  ngOnDestroy() {
    this.destroy$.next(null);
    this.destroy$.complete();
  }
}
