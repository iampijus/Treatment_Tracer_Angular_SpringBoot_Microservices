import { Component } from '@angular/core';
import { Login } from '../../model/login';
import { FormsModule } from '@angular/forms';
import { CommonModule } from '@angular/common';
import { RouterLink } from '@angular/router';
import { LoginRegisterService } from '../../services/login-register.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  standalone: true,
  imports: [FormsModule, CommonModule, RouterLink],
  templateUrl: './login.component.html',
  styleUrl: './login.component.css',
})
export class LoginComponent {
  userData: Login = new Login();

  constructor(
    private loginRegisterService: LoginRegisterService,
    private router: Router
  ) {}

  handleSubmit() {
    console.log(this.userData);
    this.loginRegisterService.login(this.userData).subscribe({
      next: (res) => {
        console.log('Login successful', res);
        // store the token in sessionStorage
        window.alert('Login successful');
        sessionStorage.setItem('authToken', res.token);
        // Navigate to the dashboard
        this.router.navigate(['/dashboard']);
      },
      error: (err) => {
        window.alert('Invalid credentials');
        console.log('Login failed', err);
      },
    });
  }
}
