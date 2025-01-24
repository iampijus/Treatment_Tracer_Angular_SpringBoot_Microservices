import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from '../model/user';

@Injectable({
  providedIn: 'root',
})
export class UserService {
  apiUrl = 'http://localhost:8081/healthcare/v2/users';

  constructor(private http: HttpClient) {}

  getUserByUsername() {
    return this.http.get<User>(`${this.apiUrl}`);
  }

  updateUser(id: any, user: User) {
    return this.http.put(`${this.apiUrl}/${id}`, user);
  }
}
