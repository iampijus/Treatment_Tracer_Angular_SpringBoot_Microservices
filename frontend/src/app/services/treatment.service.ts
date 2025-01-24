import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class TreatmentService {
  private apiUrl = 'http://localhost:8081/healthcare/v1/treatments';

  constructor(private http: HttpClient) {}

  getTreatments(): Observable<any> {
    return this.http.get<any>(this.apiUrl);
  }
}
