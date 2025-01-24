import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class BookmarkService {
  apiUrl = 'http://localhost:8081/healthcare/v3/bookmarks';

  constructor(private http: HttpClient) {}

  getBookmarks(): Observable<any> {
    return this.http.get<any>(`${this.apiUrl}`);
  }

  addBookmark(treatment: any): Observable<any> {
    return this.http.post<any>(`${this.apiUrl}`, treatment);
  }

  deleteBookmark(id: any): Observable<any> {
    return this.http.delete<any>(`${this.apiUrl}/${id}`);
  }
}
