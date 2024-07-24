import {ApplicationConfig} from '@angular/core';
import {provideRouter} from '@angular/router';

import {routes} from './app.routes';
import {HTTP_INTERCEPTORS, provideHttpClient, withInterceptorsFromDi} from "@angular/common/http";
import {MessageModule} from "primeng/message";
import {MessageService} from "primeng/api";
import {provideAnimations} from "@angular/platform-browser/animations";
import {AuthInterceptor} from "./http-interceptors/auth-interceptor";
import {AuthGuard} from "./auth/auth.guard";

export const appConfig: ApplicationConfig = {
  providers: [
    provideHttpClient(withInterceptorsFromDi()),
    {
      provide: HTTP_INTERCEPTORS,
      useClass: AuthInterceptor,
      multi: true
    },
    provideRouter(routes),
    provideHttpClient(),
    provideAnimations(),
    MessageModule,
    MessageService,
    AuthGuard
  ]
};
