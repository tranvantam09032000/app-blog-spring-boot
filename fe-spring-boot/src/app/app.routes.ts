import {Routes} from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {PostsByCategoryComponent} from "./pages/posts-by-category/posts-by-category.component";
import {FormPostComponent} from "./pages/form-post/form-post.component";
import {ViewPostComponent} from "./pages/view-post/view-post.component";
import {LoginComponent} from "./pages/login/login.component";
import {AuthGuard} from "./auth/auth.guard";

export const routes: Routes = [
  {path: '', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'login', component: LoginComponent},
  {path: 'home', component: HomeComponent, canActivate: [AuthGuard]},
  {path: 'posts-by-category/:id', component: PostsByCategoryComponent, canActivate: [AuthGuard]},
  {path: 'post/view/:id', component: ViewPostComponent, canActivate: [AuthGuard]},
  {path: 'post/create', component: FormPostComponent, canActivate: [AuthGuard]},
  {path: 'post/update/:id', component: FormPostComponent, canActivate: [AuthGuard]},
];
