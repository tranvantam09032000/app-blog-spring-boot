import {Component, OnDestroy, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {MenubarModule} from "primeng/menubar";
import {MenuItem, PrimeTemplate} from "primeng/api";
import {Subject, takeUntil} from "rxjs";
import {ActivatedRoute, NavigationStart, Router} from "@angular/router";
import {CategoryService} from "../../services/category.service";

@Component({
  selector: 'app-menu-bar',
  standalone: true,
  imports: [
    Button,
    MenubarModule,
    PrimeTemplate
  ],
  templateUrl: './menu-bar.component.html',
  styleUrl: './menu-bar.component.scss'
})
export class MenuBarComponent implements OnInit, OnDestroy {
  items: MenuItem[] = [
    {
      label: 'Home',
      icon: 'pi pi-home',
      routerLink: '/home'
    },
  ];
  isShowMenu: boolean = false;
  isButtonCreate: boolean = false;
  isButtonUpdate: boolean = false;
  id: string = "";
  destroy$ = new Subject();

  constructor(private router: Router, private route: ActivatedRoute, private categoryService: CategoryService) {

  }

  ngOnInit() {
    this.router.events.subscribe((event: any) => {
      window.scrollTo(0, 0);
      if (event instanceof NavigationStart) {
        this.id = event.url.split("/").pop() || "";
        this.isButtonCreate = !event.url.includes('/post/create');
        this.isButtonUpdate = event.url.includes(`/post/view/`);
      }
    });

    this.getCategories();
  }

  getCategories() {
    this.categoryService.getCategories().pipe(takeUntil(this.destroy$)).subscribe((values) => {
      const data = {
        label: 'Categories',
        icon: 'pi pi-star',
        items: values.map((item) => ({
          label: item.title, id: item.id.toString(),
          routerLink: `/posts-by-category/${item.id}`,
        }))
      };
      this.items?.push(data)
      this.isShowMenu = true;
    })
  }

  ngOnDestroy() {
    this.destroy$.next(null);
  }
}
