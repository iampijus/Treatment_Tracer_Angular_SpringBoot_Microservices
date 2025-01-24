import { Component, OnInit } from '@angular/core';
import { FormsModule } from '@angular/forms';
import { UserService } from '../../services/user.service';
import { User } from '../../model/user';
import { Router } from '@angular/router';

@Component({
  selector: 'app-profile',
  standalone: true,
  imports: [FormsModule],
  templateUrl: './profile.component.html',
  styleUrl: './profile.component.css',
})
export class ProfileComponent implements OnInit {
  constructor(private userService: UserService, private router: Router) {}

  ngOnInit() {
    this.getUserByDetails();
  }

  isEditMode: boolean = false;
  user: User = new User();

  getUserByDetails() {
    this.userService.getUserByUsername().subscribe({
      next: (data) => {
        this.user = data;
        console.log(data);
      },
      error: (err) => console.log(err),
    });
  }

  toggleEditMode() {
    this.isEditMode = !this.isEditMode;
  }

  saveDetails() {
    this.isEditMode = false;
    this.userService.updateUser(this.user.id, this.user).subscribe({
      next: (res) => {
        console.log(res);
        console.log('User details saved successfully');
        this.getUserByDetails();
      },
      error: (err) => console.log(err),
    });
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
