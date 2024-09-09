import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {catchError, Observable, switchMap, tap, throwError} from "rxjs";
import {AuthService} from "../services/auth.service";
import {CookieService} from "ngx-cookie-service";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {
  token = "";
  apis: any[] = [];

  constructor(private authService: AuthService, private cookie: CookieService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
    let authReq  = this.addTokenHeader(req);

    return next.handle(authReq).pipe(
      catchError(error => {
        if (error instanceof HttpErrorResponse && (error.status === 401 || error.status === 403)) {
          this.apis.push({authReq: authReq, next: next})
          return this.handle401Error(authReq, next);
        }
        return throwError(error);
      })
    );
  }

  private addTokenHeader(request: HttpRequest<any>) {
    this.token = this.cookie.get("token");
    return this.token && !request.url.includes("auth/login") && !request.url.includes("auth/refresh") ? request.clone({
        headers: request.headers.set('Authorization', 'Bearer ' + this.token)
      }) :
      request.clone({})
    ;
  }

  private handle401Error(request: HttpRequest<any>, next: HttpHandler) {
    // if (this.authService.isRefreshing$.getValue() && !this.authService.isRefreshed$.getValue()) {
    //   this.authService.isRefreshing$.next(false);
    //   this.authService.isRefreshed$.next(true);
    //   return this.refreshToken(request, next);
    // } else {
    //   return next.handle(this.addTokenHeader(request));
    // }
    return this.refreshToken(request, next);
  }

  private refreshToken(request: HttpRequest<any>, next: HttpHandler) {
    return this.authService.refreshToken({token: this.token}).pipe(
      switchMap((value) => {
        console.log("run refresh")
        this.cookie.set("token", value.token)
        return next.handle(this.addTokenHeader(request));
      }),
      catchError((err) => {
        this.cookie.deleteAll();
        this.router.navigateByUrl("/login");
        this.authService.isRefreshing$.next(true);
        this.authService.isRefreshing$.next(false);
        return throwError(err);
      })
    );
  }
}
