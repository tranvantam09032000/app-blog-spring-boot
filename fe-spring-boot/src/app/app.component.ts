import {Component} from '@angular/core';
import {MenuBarComponent} from "./components/menu-bar/menu-bar.component";
import {NavigationStart, Router, RouterOutlet} from "@angular/router";
import {FooterComponent} from "./components/footer/footer.component";
import {ToastModule} from "primeng/toast";

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
export class AppComponent {
  title = 'fe-spring-boot';
  isShow = true;

  constructor(private router: Router) {

    this.router.events.subscribe((event) => {
      if (event instanceof NavigationStart) {
        this.isShow = event.url !== "/login"
      }
    });

  }
}
