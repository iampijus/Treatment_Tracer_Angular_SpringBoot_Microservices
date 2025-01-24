import { HttpInterceptorFn } from '@angular/common/http';

export const authInterceptor: HttpInterceptorFn = (req, next) => {
  // Exclude login, registration, and treatment endpoints
  if (
    req.url.includes('/login') ||
    req.url.includes('/register') ||
    req.url.includes('/treatment')
  ) {
    return next(req);
  }

  const token = sessionStorage.getItem('authToken');

  if (token) {
    const cloned = req.clone({
      headers: req.headers.set('Authorization', `Bearer ${token}`),
    });
    return next(cloned);
  }
  return next(req);
};
