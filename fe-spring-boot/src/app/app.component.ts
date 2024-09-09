import {Component, OnDestroy} from '@angular/core';
import {MenuBarComponent} from "./components/menu-bar/menu-bar.component";
import {NavigationStart, Router, RouterOutlet} from "@angular/router";
import {FooterComponent} from "./components/footer/footer.component";
import {ToastModule} from "primeng/toast";
import {AuthService} from "./services/auth.service";
import {CookieService} from "ngx-cookie-service";
import {Subject} from "rxjs";

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [
    MenuBarComponent,
    RouterOutlet,
    FooterComponent,
    ToastModule
  ],
  templateUrl: './app.component.html',
  styleUrl: './app.component.scss'
})
export class AppComponent implements OnDestroy{
  title = 'fe-spring-boot';
  isShow = false;
  destroy$ = new Subject();

  constructor(private router: Router, private authService: AuthService,private cookie: CookieService) {

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.isShow = event.url !== "/login"
      }
    });

  }

  ngOnDestroy() {
    this.destroy$.next(null);
    this.destroy$.complete();
  }
}
