import { inject } from '@angular/core';
import { CanActivateFn } from '@angular/router';
import { Router } from '@angular/router';

export const authGuard: CanActivateFn = (route, state) => {
  const router = inject(Router);
  let token: string | null = null;
  if (typeof sessionStorage !== 'undefined') {
    token = sessionStorage.getItem('authToken');
  }
  if (token) {
    return true;
  } else {
    router.navigate(['/login']);
    return false;
  }
};
