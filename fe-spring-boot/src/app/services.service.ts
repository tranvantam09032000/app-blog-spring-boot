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
    return this.httpClient.get<IAuthor[]>(`http://localhost:8080/identity/authors`)
  }

  getTags() {
    return this.httpClient.get<ITag[]>(`http://localhost:8080/identity/tags`)
  }

  getCategories() {
    return this.httpClient.get<ICategory[]>(`http://localhost:8080/identity/categories`)
  }

  getCategoryById(id: string) {
    return this.httpClient.get<ICategory>(`http://localhost:8080/identity/categories/${id}`)
  }

  getPosts(filter: IFilterPost) {
    const params = this.getURLSearchParams(filter);
    return this.httpClient.get<IPost[]>(`http://localhost:8080/identity/posts?${params}`)
  }

  getPostById(id: string) {
    return this.httpClient.get<IPost>(`http://localhost:8080/identity/posts/${id}`)
  }

  createPost(body: IFormPost) {
    return this.httpClient.post<number>(`http://localhost:8080/identity/posts`, body)
  }

  updatePost(body: IFormPost) {
    return this.httpClient.put<number>(`http://localhost:8080/identity/posts/${body.id}`, body)
  }

  deletePost(id: string) {
    return this.httpClient.delete(`http://localhost:8080/identity/posts/${id}`)
  }

  getCommentsByPost(id: string) {
    return this.httpClient.get<IComment[]>(`http://localhost:8080/identity/comments/${id}`)
  }

  createComment(body: IFormComment) {
    return this.httpClient.post(`http://localhost:8080/identity/comments`, body)
  }

  updateComment(body: IFormComment) {
    return this.httpClient.put(`http://localhost:8080/identity/comments/${body.id}`, body)
  }
}
