import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MatCardModule } from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import { MatIconModule } from '@angular/material/icon';
import { TreatmentService } from '../../services/treatment.service';
import { BookmarkService } from '../../services/bookmark.service';
import { CurrencyPipe } from '@angular/common';
import { FormsModule } from '@angular/forms';

@Component({
  selector: 'app-treatment',
  standalone: true,
  imports: [
    CommonModule,
    MatCardModule,
    MatButtonModule,
    MatIconModule,
    CurrencyPipe,
    FormsModule,
  ],
  templateUrl: './treatment.component.html',
  styleUrl: './treatment.component.css',
})
export class TreatmentComponent implements OnInit {
  treatments: any[] = [];
  selectedTreatment: any;
  bookmarks: any[] = [];
  isLoading: boolean = false;
  isBookmarked: boolean = false;
  isAlreadyBookmarked: boolean = false;
  // pagination
  displayedTreatments: any[] = [];
  currentPage: number = 1;
  pageSize: number = 100;
  totalPages: number = 0;

  // search and filter treatments
  filteredTreatments: any[] = [];
  searchTerm: string = '';
  insurances: string[] = [];

  constructor(
    private treatmentService: TreatmentService,
    private bookmarkService: BookmarkService
  ) {}
  ngOnInit(): void {
    this.fetchTreatments();
  }

  // Fetch treatments data from the API
  fetchTreatments() {
    // loading while fetching data
    this.isLoading = true;
    this.treatmentService.getTreatments().subscribe({
      next: (data) => {
        this.treatments = data;
        this.filteredTreatments = data;
        // Extract unique hospital names and insurance provider names

        const insuranceSet = new Set<string>();
        this.treatments.forEach((treat) => {
          insuranceSet.add(treat.insurance_provider);
        });
        this.insurances = Array.from(insuranceSet);

        //pagination
        this.totalPages = Math.ceil(this.treatments.length / this.pageSize);
        this.updateDisplayedTreatments();
        // stop loading spinner
        this.isLoading = false;
      },
      error: (err) => {
        console.log(err);
        this.isLoading = false;
      },
    });
  }

  updateDisplayedTreatments() {
    const startInd = (this.currentPage - 1) * this.pageSize;
    const endInd = startInd + this.pageSize;
    this.displayedTreatments = this.filteredTreatments.slice(startInd, endInd);
  }

  prevPage() {
    if (this.currentPage > 1) {
      this.currentPage--;
      this.updateDisplayedTreatments();
    }
  }
  nextPage() {
    if (this.currentPage < this.totalPages) {
      this.currentPage++;
      this.updateDisplayedTreatments();
    }
  }

  // bookmark the treatment
  bookmarkTreatment(treatment: any) {
    const isPresent = this.bookmarks.find(
      (bookmark) => bookmark.id === treatment.id
    );

    if (isPresent) {
      this.isAlreadyBookmarked = true;
      console.log('Treatment is already bookmarked');
      setTimeout(() => {
        this.isAlreadyBookmarked = false;
      }, 1000);
      return;
    } else {
      this.bookmarkService.addBookmark(treatment).subscribe({
        next: (data) => {
          this.isBookmarked = true;
          this.bookmarks.push(treatment);
          setTimeout(() => {
            this.isBookmarked = false;
          }, 1000);
        },
        error: (err) => {
          // alert for bookmark already present
          this.isAlreadyBookmarked = true;
          console.log(err);
          setTimeout(() => {
            this.isAlreadyBookmarked = false;
          }, 1000);
        },
      });
    }
  }

  // for bootstrap modal
  getTreatmentDetails(treatment: any) {
    this.selectedTreatment = treatment;
  }

  // check if the user is logged in for showing the bookmark button on treatments card
  isLoggedIn(): boolean {
    if (typeof sessionStorage !== 'undefined') {
      const token = sessionStorage.getItem('authToken');
      if (token) {
        return true;
      }
    }
    return false;
  }

  // filter treatments based on search term
  searchTreatments() {
    this.isLoading = true;
    this.filteredTreatments = this.treatments.filter((treatment) =>
      treatment.medical_Condition
        .toLowerCase()
        .includes(this.searchTerm.toLowerCase())
    );
    this.totalPages = Math.ceil(this.filteredTreatments.length / this.pageSize);
    this.currentPage = 1;
    this.updateDisplayedTreatments();
    // stop loading spinner
    setTimeout(() => {
      this.isLoading = false;
    }, 500);
  }

  // filter treatments based on insurance provider
  filterByInsuranceProvider(event: Event) {
    this.isLoading = true;
    const target = event.target as HTMLSelectElement;
    const provider = target.value;
    if (provider) {
      this.filteredTreatments = this.treatments.filter(
        (treatment) => treatment.insurance_provider === provider
      );
    } else {
      this.filteredTreatments = this.treatments;
    }
    this.totalPages = Math.ceil(this.filteredTreatments.length / this.pageSize);
    this.currentPage = 1;
    this.updateDisplayedTreatments();
    setTimeout(() => {
      this.isLoading = false; // Stop loading spinner
    }, 500);
  }
}
