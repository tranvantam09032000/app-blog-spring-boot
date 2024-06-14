import {Component, OnDestroy, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {CardModule} from "primeng/card";
import {DataViewModule} from "primeng/dataview";
import {MenubarModule} from "primeng/menubar";
import {DatePipe, NgClass, NgForOf} from "@angular/common";
import {MenuItem, PrimeTemplate} from "primeng/api";
import {ICategory, IFilterPost, IPost} from "../../models.model";
import {combineLatest, map, Observable, Subject, takeUntil} from "rxjs";
import {ServicesService} from "../../services.service";
import {ImageModule} from "primeng/image";
import {CardPostComponent} from "../../components/card-post/card-post.component";
import {format} from "date-fns";

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
export class HomeComponent implements OnInit, OnDestroy{
  posts: IPost[] = [];
  postNew?: IPost;
  post5TopNew?: IPost[];
  post4TopNew?: IPost[];
  filterPost: IFilterPost = {page: 1, size: 10, categoryId: ""}
  postsByCategory: IPostByCategory[] = [];
  destroy$ = new Subject();
  constructor(private servicesService:ServicesService) {
  }
  ngOnInit() {
    this.getPosts(this.filterPost);
    this.getCategories();
  }

  getPosts(filter: IFilterPost) {
    this.servicesService.getPosts(filter).pipe(takeUntil(this.destroy$)).subscribe((values)=> {
      this.posts = values.map(item=> ({...item}));
      this.postNew = this.posts[0];
      this.post5TopNew = this.posts.slice(1,6);
      this.post4TopNew = this.posts.slice(6,10);
    })
  }

  getCategories() {
    this.postsByCategory = [];
    this.servicesService.getCategories().pipe(
      takeUntil(this.destroy$)
    ).subscribe((values)=> {
      values.forEach(item=> {
        this.getPostByCategory(item)
      })
    })
  }

  getPostByCategory(category: ICategory): Observable<IPostByCategory> {
    return this.servicesService.getPosts({page: 1, size: 4, categoryId: category.id.toString()}).pipe(
      map((values)=> {
        return {
          category: category,
          posts: values
        }
      }),
      takeUntil(this.destroy$)
    )
  }
  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
