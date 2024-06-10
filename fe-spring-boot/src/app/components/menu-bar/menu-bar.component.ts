import {Component, OnDestroy, OnInit} from '@angular/core';
import {Button} from "primeng/button";
import {MenubarModule} from "primeng/menubar";
import {MenuItem, PrimeTemplate} from "primeng/api";
import {Subject, takeUntil} from "rxjs";
import {ICategory} from "../../models.model";
import {ServicesService} from "../../services.service";
import {ActivatedRoute, NavigationStart, Router} from "@angular/router";

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
export class MenuBarComponent implements OnInit, OnDestroy{
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
  constructor(private router : Router,private route: ActivatedRoute,private servicesService:ServicesService) {

  }
  ngOnInit() {
    this.router.events.subscribe((event: any) => {
      window.scrollTo(0,0);
      if (event instanceof NavigationStart) {
        this.id = event.url.split("/").pop() || "";
        this.isButtonCreate = !event.url.includes('/post/create');
        this.isButtonUpdate = event.url.includes(`/post/view/`);
      }
    });

    this.getCategories();
  }
  getCategories() {
    this.servicesService.getCategories().pipe(takeUntil(this.destroy$)).subscribe((values)=> {
      const data = {
        label: 'Categories',
        icon: 'pi pi-star',
        items: values.map((item: ICategory)=> ({
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
