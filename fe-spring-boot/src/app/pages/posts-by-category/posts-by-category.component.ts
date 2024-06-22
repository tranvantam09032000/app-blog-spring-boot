import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Subject, takeUntil} from "rxjs";
import {CardModule} from "primeng/card";
import {ImageModule} from "primeng/image";
import {DataViewModule} from "primeng/dataview";
import {NgClass, NgForOf} from "@angular/common";
import {MessageService} from "primeng/api";
import {format} from "date-fns";
import {IFilterPost, IPost} from "../../models/product.model";
import {ICategory} from "../../models/category.model";
import {CategoryService} from "../../services/category.service";
import {PostService} from "../../services/post.service";

@Component({
  selector: 'app-posts-by-category',
  standalone: true,
  imports: [
    CardModule,
    ImageModule,
    DataViewModule,
    NgForOf,
    NgClass,
    RouterLink,
  ],
  templateUrl: './posts-by-category.component.html',
  styleUrl: './posts-by-category.component.scss'
})
export class PostsByCategoryComponent implements OnInit, OnDestroy {
  filter: IFilterPost = {
    page: 1,
    size: 1000,
    categoryId: ""
  }
  posts: IPost[] = [];
  category?: ICategory
  destroy$ = new Subject();

  constructor(private route: ActivatedRoute, private categoryService: CategoryService,
              private postService: PostService,
              private router: Router, private messageService: MessageService) {
  }

  ngOnInit() {
    this.route.paramMap.pipe(
      takeUntil(this.destroy$)
    ).subscribe((params) => {
      const id = params.get('id') || '';
      if (id) {
        this.getCategoryById(id);
      }
    });
  }

  getCategoryById(id: string) {
    this.categoryService.getCategoryById(id).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      {
        next: (category) => {
          this.category = category;
          this.filter.categoryId = this.category?.id.toString() || "";
          this.getPostsByCategory(this.filter);
        },
        error: (error) => {
          this.messageService.add({severity: 'error', summary: 'Error', detail: 'Category not found'});

          this.router.navigateByUrl("/home");
        }
      }
    )
  }

  getPostsByCategory(filter: IFilterPost) {
    this.postService.getPosts(filter).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      (posts) => {
        this.posts = posts.map(item => ({...item, createdAt: format(new Date(item.createdAt), "yyyy/MM/dd HH:mm")}));
      }
    )
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
