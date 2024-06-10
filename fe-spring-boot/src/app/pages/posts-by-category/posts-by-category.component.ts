import {Component, OnDestroy, OnInit} from '@angular/core';
import {ActivatedRoute, Router, RouterLink} from "@angular/router";
import {Subject, takeUntil} from "rxjs";
import {ServicesService} from "../../services.service";
import {ICategory, IFilterPost, IPost} from "../../models.model";
import {CardModule} from "primeng/card";
import {ImageModule} from "primeng/image";
import {DataViewModule} from "primeng/dataview";
import {CommonModule, NgClass, NgForOf} from "@angular/common";
import {MessageService} from "primeng/api";
import {format} from "date-fns";

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
export class PostsByCategoryComponent implements OnInit, OnDestroy{
  filter: IFilterPost = {
    page: 1,
    size: 1000,
    categoryId: ""
  }
  posts: IPost[] = [];
  category?: ICategory
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
        this.getCategoryById(id);
      }
    });
  }

  getCategoryById(id: string) {
  this.servicesService.getCategoryById(id).pipe(
    takeUntil(this.destroy$)
  ).subscribe(
    {
      next: (category)=> {
        this.category = category;
        this.filter.categoryId = this.category.id.toString();
        this.getPostsByCategory(this.filter);
      },
      error: (error)=> {
        this.messageService.add({ severity: 'error', summary: 'Error', detail: 'Category not found' });

        this.router.navigateByUrl("/home");
      }
    }
  )
}

getPostsByCategory(filter: IFilterPost) {
    this.servicesService.getPosts(filter).pipe(
      takeUntil(this.destroy$)
    ).subscribe(
      (posts)=> {
        this.posts = posts.map(item=> ({...item, createdAt: format(new Date(item.createdAt), "yyyy/MM/dd HH:mm")}));
      }
    )
}

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
