import { Component } from '@angular/core';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';
import { LoginRegisterService } from '../../services/login-register.service';
import {
  ReactiveFormsModule,
  FormGroup,
  FormControl,
  Validators,
} from '@angular/forms';
import { Register } from '../../model/register';

@Component({
  selector: 'app-register',
  standalone: true,
  imports: [RouterLink, ReactiveFormsModule, CommonModule],
  templateUrl: './register.component.html',
  styleUrl: './register.component.css',
})
export class RegisterComponent {
  registerForm: any;
  mobileRegex: string = '[6789][0-9]{9}';
  emailRegex: string = '[a-z0-9._%+-]+@[a-z0-9.-]+.[a-z]{2,}$';

  constructor(private loginRegistrationService: LoginRegisterService) {
    this.registerForm = new FormGroup({
      name: new FormControl('', [Validators.required, Validators.minLength(5)]),
      email: new FormControl('', [
        Validators.required,
        Validators.pattern(this.emailRegex),
      ]),
      mobile: new FormControl('', [
        Validators.required,
        Validators.pattern(this.mobileRegex),
      ]),
      age: new FormControl('', [Validators.required]),
      gender: new FormControl('', [Validators.required]),
      password: new FormControl('', [
        Validators.required,
        Validators.minLength(5),
      ]),
    });
  }

  get name() {
    return this.registerForm.get('name');
  }
  get email() {
    return this.registerForm.get('email');
  }
  get mobile() {
    return this.registerForm.get('mobile');
  }
  get age() {
    return this.registerForm.get('age');
  }
  get gender() {
    return this.registerForm.get('gender');
  }
  get password() {
    return this.registerForm.get('password');
  }

  onSubmit() {
    console.log(this.registerForm.value);
    if (this.registerForm.valid) {
      const registerData: Register = this.registerForm.value;
      this.loginRegistrationService.register(registerData).subscribe({
        next: (res) => {
          window.alert('Registration successful');
          this.registerForm.reset();
          console.log('Registration successful', res);
        },
        error: (error) => {
          window.alert('Registration failed');
          this.registerForm.reset();
          console.log('Registration failed', error);
        },
      });
    } else {
      console.log('Form is invalid');
    }
  }
}
