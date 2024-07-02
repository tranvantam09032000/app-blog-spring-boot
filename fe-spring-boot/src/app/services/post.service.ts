import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {getURLSearchParams} from "../shared/utilities";
import {URL_BASE} from "../shared/const";
import {IFilterPost, IFormPost, IPost} from "../models/product.model";

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private httpClient: HttpClient) {
  }

  getPosts(filter: IFilterPost) {
    const params = getURLSearchParams(filter);
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

  likePost(body: {postId: number, authorId: number}) {
    return this.httpClient.post<boolean>(`${URL_BASE}/posts/like`, body)
  }

  deletePost(id: string) {
    return this.httpClient.delete(`${URL_BASE}/posts/${id}`)
  }
}
