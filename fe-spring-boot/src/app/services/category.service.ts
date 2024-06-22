import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {URL_BASE} from "../shared/const";
import {ICategory} from "../models/category.model";

@Injectable({
  providedIn: 'root'
})
export class CategoryService {

  constructor(private httpClient: HttpClient) {
  }

  getCategories() {
    return this.httpClient.get<ICategory[]>(`${URL_BASE}/categories`)
  }

  getCategoryById(id: string) {
    return this.httpClient.get<ICategory>(`${URL_BASE}/categories/${id}`)
  }
}
