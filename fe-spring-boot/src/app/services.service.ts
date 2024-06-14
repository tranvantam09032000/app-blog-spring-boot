import { Injectable } from '@angular/core';
import {HttpClient, HttpParams} from "@angular/common/http";
import {
  IAuthor,
  ICategory,
  IComment,
  IFilterPost,
  IFormComment,
  IFormPost,
  IPost,
  IResponse,
  ITag
} from "./models.model";
import {map, Observable} from "rxjs";
const URL_BASE = "http://localhost:8888/identity"
@Injectable({
  providedIn: 'root'
})
export class ServicesService {
  constructor(private httpClient: HttpClient) { }

  getURLSearchParams(filter: Record<string, any>) {
    const params = new URLSearchParams();

    Object.entries(filter).forEach(([key, value]) => {
      if (!value || (Array.isArray(value) && value.length === 0)) return;

      params.set(key, value);
    });

    return params;
  }

  getAuthors() {
    return this.httpClient.get<IAuthor[]>(`${URL_BASE}/authors`)
  }

  getTags() {
    return this.httpClient.get<ITag[]>(`${URL_BASE}/tags`)
  }

  getCategories() {
    return this.httpClient.get<ICategory[]>(`${URL_BASE}/categories`)
  }

  getCategoryById(id: string) {
    return this.httpClient.get<ICategory>(`${URL_BASE}/categories/${id}`)
  }

  getPosts(filter: IFilterPost) {
    const params = this.getURLSearchParams(filter);
    return this.httpClient.get<IPost[]>(`${URL_BASE}/posts?${params}`)
  }

  getPostById(id: string) {
    return this.httpClient.get<IPost>(`${URL_BASE}/posts/${id}`)
  }

  createPost(body: IFormPost) {
    return this.httpClient.post<number>(`${URL_BASE}/posts`, body)
  }

  updatePost(body: IFormPost) {
    return this.httpClient.put<number>(`${URL_BASE}/posts/${body.id}`, body)
  }

  deletePost(id: string) {
    return this.httpClient.delete(`${URL_BASE}/posts/${id}`)
  }

  getCommentsByPost(id: string) {
    return this.httpClient.get<IComment[]>(`${URL_BASE}/comments?postId=${id}`)
  }

  createComment(body: IFormComment) {
    return this.httpClient.post(`${URL_BASE}/comments`, body)
  }

  updateComment(body: IFormComment) {
    return this.httpClient.put(`${URL_BASE}/comments/${body.id}`, body)
  }
}
