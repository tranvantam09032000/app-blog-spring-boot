import { Routes } from '@angular/router';
import {HomeComponent} from "./pages/home/home.component";
import {PostsByCategoryComponent} from "./pages/posts-by-category/posts-by-category.component";
import {FormPostComponent} from "./pages/form-post/form-post.component";
import {ViewPostComponent} from "./pages/view-post/view-post.component";

export const routes: Routes = [
  {path: '', component: HomeComponent},
  {path: 'home', component: HomeComponent},
  {path: 'posts-by-category/:id', component: PostsByCategoryComponent},
  {path: 'post/view/:id', component: ViewPostComponent},
  {path: 'post/create', component: FormPostComponent},
  {path: 'post/update/:id', component: FormPostComponent},
];
