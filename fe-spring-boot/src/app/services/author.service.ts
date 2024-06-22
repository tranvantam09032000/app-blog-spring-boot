import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {URL_BASE} from "../shared/const";
import {IAuthor} from "../models/author.model";

@Injectable({
  providedIn: 'root'
})
export class AuthorService {
  constructor(private httpClient: HttpClient) {
  }

  getAuthors() {
    return this.httpClient.get<IAuthor[]>(`${URL_BASE}/authors`)
  }
}
