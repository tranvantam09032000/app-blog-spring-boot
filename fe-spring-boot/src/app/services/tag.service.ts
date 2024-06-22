import {Injectable} from '@angular/core';
import {URL_BASE} from "../shared/const";
import {HttpClient} from "@angular/common/http";
import {ITag} from "../models/tag.model";

@Injectable({
  providedIn: 'root'
})
export class TagService {
  constructor(private httpClient: HttpClient) {
  }

  getTags() {
    return this.httpClient.get<ITag[]>(`${URL_BASE}/tags`)
  }
}
