import { Component } from '@angular/core';
import { AuthService } from './auth.service';
import { NgForm } from '@angular/forms';

@Component({
  selector: 'login-form',
  providers: [AuthService],
  templateUrl: './login.component.html'
})
export class LoginComponent {
    public loginData = {username: "", password: ""};

    constructor(private authService:AuthService) {}

    login(ngForm: NgForm) {
      this.authService.getAndSaveAccessToken(ngForm);
    }
}
