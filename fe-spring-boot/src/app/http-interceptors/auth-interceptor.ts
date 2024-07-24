import {Injectable} from "@angular/core";
import {HttpErrorResponse, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {CookieService} from "ngx-cookie-service";
import {tap} from "rxjs";
import {Router} from "@angular/router";

@Injectable()
export class AuthInterceptor implements HttpInterceptor {

  constructor(private cookie: CookieService, private router: Router) {
  }

  intercept(req: HttpRequest<any>, next: HttpHandler) {
    const authToken = this.cookie.get("token");
    let authReq = req.clone({});
    if (authToken) {
      authReq = req.clone({
        headers: req.headers.set('Authorization', 'Bearer ' + authToken)
      });
    }

    return next.handle(authReq).pipe(tap(
      event => {
      },
      error => {
        if (error instanceof HttpErrorResponse) {
          if (error.status === 401) {
            this.cookie.delete("token");
            this.router.navigateByUrl("/login");
          }
        }
      }
    ));
  }
}
