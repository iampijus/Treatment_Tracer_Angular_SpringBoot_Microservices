import { Component } from '@angular/core';
import { RouterOutlet, RouterLink } from '@angular/router';
import { DashboardComponent } from './components/dashboard/dashboard.component';
import { CommonModule } from '@angular/common';
import { Router } from '@angular/router';

@Component({
  selector: 'app-root',
  standalone: true,
  imports: [RouterOutlet, RouterLink, DashboardComponent, CommonModule],
  templateUrl: './app.component.html',
  styleUrl: './app.component.css',
})
export class AppComponent {
  title = 'treatment-tracer';

  constructor(private router: Router) {}

  isLoggedIn(): boolean {
    if (typeof sessionStorage !== 'undefined') {
      const token = sessionStorage.getItem('authToken');
      if (token) {
        return true;
      }
    }
    return false;
  }

  logout() {
    const confirmed = window.confirm('Are you sure you want to logout?');
    if (confirmed) {
      // Check if sessionStorage is available before removing the token
      if (typeof sessionStorage !== 'undefined') {
        sessionStorage.removeItem('authToken');
      }
      this.router.navigate(['/login']);
    }
  }
}
