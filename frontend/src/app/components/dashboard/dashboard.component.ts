import { Component } from '@angular/core';
import { MatButtonModule } from '@angular/material/button';
import { MatCardModule } from '@angular/material/card';
import { RouterLink } from '@angular/router';
import { CommonModule } from '@angular/common';

@Component({
  selector: 'app-dashboard',
  standalone: true,
  imports: [RouterLink, MatButtonModule, MatCardModule,CommonModule],
  templateUrl: './dashboard.component.html',
  styleUrl: './dashboard.component.css',
})
export class DashboardComponent {
  featuredTreatments = [
    {
      Medical_Condition: 'Cancer',
      Hospital: 'Sons and Miller',
      Billing_Amount: 18856.28,
      Insurance_Provider: 'Blue Cross',
    },
    {
      Medical_Condition: 'Obesity',
      Hospital: 'Kim Inc',
      Billing_Amount: 33643.33,
      Insurance_Provider: 'Medicare',
    },
    {
      Medical_Condition: 'Diabetes',
      Hospital: 'General Health',
      Billing_Amount: 12540.5,
      Insurance_Provider: 'Cigna',
    },
  ];

  isLoggedIn(): boolean {
    if (typeof sessionStorage !== 'undefined') {
      const token = sessionStorage.getItem('authToken');
      if (token) {
        return true;
      }
    }
    return false;
  } 
}
