import {Component, OnDestroy, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {CardModule} from "primeng/card";
import {DataViewModule} from "primeng/dataview";
import {MenubarModule} from "primeng/menubar";
import {DatePipe, NgClass, NgForOf} from "@angular/common";
import {map, forkJoin, Observable, Subject, takeUntil, switchAll, mergeMap, from} from "rxjs";
import {ImageModule} from "primeng/image";
import {CardPostComponent} from "../../components/card-post/card-post.component";
import {ICategory} from "../../models/category.model";
import {IFilterPost, IPost} from "../../models/product.model";
import {PrimeTemplate} from "primeng/api";
import {PostService} from "../../services/post.service";
import {CategoryService} from "../../services/category.service";
import {combineLatest} from "rxjs/internal/operators/combineLatest";

type IPostByCategory = {
  category: ICategory,
  posts: IPost[]
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    Button,
    CardModule,
    DataViewModule,
    MenubarModule,
    NgForOf,
    PrimeTemplate,
    NgClass,
    ImageModule,
    CardPostComponent,
    DatePipe
  ],
  templateUrl: './home.component.html',
  styleUrl: './home.component.scss'
})
export class HomeComponent implements OnInit, OnDestroy {
  posts: IPost[] = [];
  postNew?: IPost;
  post5TopNew?: IPost[];
  post4TopNew?: IPost[];
  filterPost: IFilterPost = {page: 1, size: 10, categoryId: ""}
  postsByCategory: IPostByCategory[] = [];
  destroy$ = new Subject();

  constructor(private postService: PostService, private categoryService: CategoryService) {
  }

  ngOnInit() {
    this.getPosts(this.filterPost);
    this.getCategories();
  }

  getPosts(filter: IFilterPost) {
    this.postService.getPosts(filter).pipe(takeUntil(this.destroy$)).subscribe((values) => {
      this.posts = values.map(item => ({...item}));
      this.postNew = this.posts[0];
      this.post5TopNew = this.posts.slice(1, 6);
      this.post4TopNew = this.posts.slice(6, 10);
    })
  }

  getCategories() {
    this.postsByCategory = [];
    this.categoryService.getCategories().pipe(
      takeUntil(this.destroy$),
      mergeMap(categories => from(categories)),
      mergeMap(category => this.postService.getPosts({page: 1, size: 4, categoryId: category.id.toString()})
        .pipe(map((posts) => {
          return {category: category, posts: posts}
        })))
    ).subscribe((value) => {
      this.postsByCategory.push(value)
    })
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
