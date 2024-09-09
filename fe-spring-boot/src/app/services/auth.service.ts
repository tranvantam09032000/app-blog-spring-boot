import {Injectable} from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {URL_BASE} from "../shared/const";
import {ILogin, ILoginResponse} from "../models/auth.model";
import {BehaviorSubject, tap} from "rxjs";
import {CookieService} from "ngx-cookie-service";

@Injectable({
  providedIn: 'root'
})
export class AuthService {

  isRefreshing$ = new BehaviorSubject<boolean>(true);
  isRefreshed$  = new BehaviorSubject<boolean>(false);
  constructor(private httpClient: HttpClient, private cookie: CookieService) {
  }

  login(body: ILogin) {
    return this.httpClient.post<ILoginResponse>(`${URL_BASE}/auth/login`, body)
  }

  refreshToken(body: {token: string}) {
    return this.httpClient.post<ILoginResponse>(`${URL_BASE}/auth/refresh`, body).pipe(
      tap(response => {
        this.cookie.set('token', response.token);
      })
    )
  }
}
