// auth.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { BehaviorSubject, Observable } from 'rxjs';
import { map } from 'rxjs/operators';
import { User } from '../../models/User';

@Injectable({ providedIn: 'root' })
export class AuthenticationService {


  constructor(private http: HttpClient) {

  }

  login(username: string, password: string): Observable<any> {

    return this.http.post<any>('http://localhost:8081/usuarios/login', { "nombreUsuario": username, "clave": password })
      .subscribe(response => {
        localStorage.setItem('token', response.token);
      });
  }

  logout(): void {
    // remove token from local storage
    localStorage.removeItem('token');
  }

  getToken(): string | null {
    return localStorage.getItem('token');
  }

  isAuthenticated(): boolean {
    // check if a token exists
    return !!this.getToken();
  }
}


