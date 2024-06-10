import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {ServicesService} from "../../services.service";
import {MessageService} from "primeng/api";
import {Subject, takeUntil} from "rxjs";
import {IFilterPost, IPost} from "../../models.model";
import {ImageModule} from "primeng/image";
import {TagModule} from "primeng/tag";
import {format} from "date-fns";
import {CommentsComponent} from "../../components/comments/comments.component";

@Component({
  selector: 'app-view-post',
  standalone: true,
  imports: [
    ImageModule,
    TagModule,
    CommentsComponent
  ],
  templateUrl: './view-post.component.html',
  styleUrl: './view-post.component.scss'
})
export class ViewPostComponent implements OnInit, OnDestroy{

  post?: IPost;
  destroy$ = new Subject();
  constructor(private route: ActivatedRoute, private servicesService:ServicesService,
              private router: Router, private messageService: MessageService) {
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
  }

  getPostById(id: string) {
    this.servicesService.getPostById(id).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      {next: (post)=> {
          this.post = {...post, createdAt: format(new Date(post.createdAt), "yyyy/MM/dd HH:mm")};
        },
        error: (error)=> {
          this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Post not found' });

          this.router.navigateByUrl("/home");
        }}
    )
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
