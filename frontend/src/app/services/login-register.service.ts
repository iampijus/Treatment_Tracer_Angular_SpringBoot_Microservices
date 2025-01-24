import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Register } from '../model/register';
import { Observable } from 'rxjs';
import { Login } from '../model/login';

@Injectable({
  providedIn: 'root',
})
export class LoginRegisterService {
  private loginUrl = 'http://localhost:8081/healthcare/auth/login';
  private registerUrl = 'http://localhost:8081/healthcare/v2/register';

  constructor(private http: HttpClient) {}

  register(registerData: Register): Observable<Register> {
    return this.http.post<Register>(`${this.registerUrl}`, registerData);
  }

  login(loginData: Login): Observable<{ token: string }> {
    return this.http.post<{ token: string }>(`${this.loginUrl}`, loginData);
  }
}
