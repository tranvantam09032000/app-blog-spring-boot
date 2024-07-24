import {Component} from '@angular/core';
import {PasswordModule} from "primeng/password";
import {CheckboxModule} from "primeng/checkbox";
import {Router, RouterLink} from "@angular/router";
import {ButtonDirective} from "primeng/button";
import {Ripple} from "primeng/ripple";
import {InputTextModule} from "primeng/inputtext";
import {FormBuilder, FormGroup, ReactiveFormsModule, Validators} from "@angular/forms";
import {AuthService} from "../../services/auth.service";
import {CookieService} from "ngx-cookie-service";
import {jwtDecode} from "jwt-decode";
import {ITokeInfo} from "../../models/auth.model";

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [
    PasswordModule,
    CheckboxModule,
    RouterLink,
    ButtonDirective,
    Ripple,
    InputTextModule,
    ReactiveFormsModule
  ],
  templateUrl: './login.component.html',
  styleUrl: './login.component.scss'
})
export class LoginComponent {
  form: FormGroup = this.formBuilder.nonNullable.group({
    email: ["tranvantam2000", Validators.required],
    password: ["123456789", Validators.required]
  });

  constructor(private authService: AuthService,
              private formBuilder: FormBuilder,
              private router: Router,
              private cookie: CookieService
  ) {
  }

  submitLogin() {
    const body = this.form.value;
    this.authService.login(body).pipe().subscribe(
      (value) => {
        const tokeInfo: ITokeInfo = jwtDecode(value.token);
        this.cookie.set("userInfo", JSON.stringify(tokeInfo.userInfo));
        this.cookie.set("token", value.token);
        this.router.navigateByUrl("/home");
      }
    )
  }
}
