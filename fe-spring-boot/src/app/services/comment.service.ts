import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {URL_BASE} from "../shared/const";
import {IComment, IFormComment} from "../models/comment.model";

@Injectable({
  providedIn: 'root'
})
export class CommentService {

  constructor(private httpClient: HttpClient) {
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
