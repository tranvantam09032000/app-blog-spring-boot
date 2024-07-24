import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {MessageService} from "primeng/api";
import {Subject, takeUntil} from "rxjs";
import {ImageModule} from "primeng/image";
import {TagModule} from "primeng/tag";
import {format} from "date-fns";
import {CommentsComponent} from "../../components/comments/comments.component";
import {GalleriaModule} from "primeng/galleria";
import {IContent, IPost} from "../../models/product.model";
import {PostService} from "../../services/post.service";
import {ComponentGenerationDirective} from "../../directives/component-generation.directive";
import {CookieService} from "ngx-cookie-service";
import {IUserInfo} from "../../models/auth.model";

@Component({
  selector: 'app-view-post',
  standalone: true,
  imports: [
    ImageModule,
    TagModule,
    CommentsComponent,
    GalleriaModule,
    ComponentGenerationDirective
  ],
  templateUrl: './view-post.component.html',
  styleUrl: './view-post.component.scss'
})
export class ViewPostComponent implements OnInit, OnDestroy {

  post: IPost = {} as IPost;
  destroy$ = new Subject();

  constructor(private route: ActivatedRoute, private postService: PostService,
              private router: Router, private messageService: MessageService,
              private cookie: CookieService) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      takeUntil(this.destroy$)
    ).subscribe((params) => {
      const id = params.get('id') || '';
      if (id) {
        this.getPostById(id);
      }
    });
  }

  getPostById(id: string) {
    this.postService.getPostById(id).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      {
        next: (post) => {
          const contents = post.contents.map((item: IContent) => {
            if (item.type === "slider") {
              const value = JSON.parse(item.value.toString().replace(/'/g, '"'));
              return {...item, value: value}
            } else {
              return item;
            }
          })
          this.post = {
            ...post,
            contents: contents.sort((a: IContent, b: IContent) => a.position - b.position),
            createdAt: format(new Date(post.createdAt), "yyyy/MM/dd HH:mm")
          };
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Post not found'});
          this.router.navigateByUrl("/home");
        }
      }
    )
  }

  likePost(postId: number) {
    const userInfo: IUserInfo = JSON.parse(this.cookie.get("userInfo"));
    const body = {
      postId: postId,
      authorId: userInfo.id
    }
    this.postService.likePost(body).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      (value) => {
        value ?
          this.post.authorsOfLike.push(body.authorId) :
          this.post.authorsOfLike = this.post?.authorsOfLike.filter(item => item !== body.authorId)
      }
    )
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
