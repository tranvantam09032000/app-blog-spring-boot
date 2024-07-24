import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {URL_BASE} from "../shared/const";
import {ILogin, ILoginResponse} from "../models/auth.model";

@Injectable({
  providedIn: 'root'
})
export class AuthService {
  constructor(private httpClient: HttpClient) {
  }

  login(body: ILogin) {
    return this.httpClient.post<ILoginResponse>(`${URL_BASE}/auth/login`, body)
  }
}
