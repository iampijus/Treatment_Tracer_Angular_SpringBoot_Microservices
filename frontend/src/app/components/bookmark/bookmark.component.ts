import { Component, OnInit } from '@angular/core';
import { BookmarkService } from '../../services/bookmark.service';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { CurrencyPipe } from '@angular/common';

@Component({
  selector: 'app-bookmark',
  standalone: true,
  imports: [MatCardModule, MatButtonModule, MatIconModule, CurrencyPipe],
  templateUrl: './bookmark.component.html',
  styleUrl: './bookmark.component.css',
})
export class BookmarkComponent implements OnInit {
  bookmarks: any[] = [];
  selectedBookmark: any;
  isLoading = false;

  constructor(private bookmarkService: BookmarkService) {}

  ngOnInit() {
    this.getBookmarks();
  }

  getBookmarks() {
    this.isLoading = true;
    this.bookmarkService.getBookmarks().subscribe({
      next: (res) => {
        console.log(res);
        this.bookmarks = res;
        setTimeout(() => {
          this.isLoading = false;
        }, 500);
      },
      error: (err) => {
        console.log(err);
        this.isLoading = false;
      },
    });
  }

  removeBookmark(id: any) {
    this.bookmarkService.deleteBookmark(id).subscribe({
      next: (res) => {
        this.getBookmarks();
        this.isLoading = false;
      },
      error: (err) => {
        console.log(err);
        this.isLoading = false;
      },
    });
  }

  // for bootstrap modal
  getDetails(treatment: any) {
    this.selectedBookmark = treatment;
  }
}
