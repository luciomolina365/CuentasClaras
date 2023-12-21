import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { Observable, throwError, catchError, BehaviorSubject, of } from 'rxjs';
import { Router } from '@angular/router';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
	currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

	constructor(private http: HttpClient,private router: Router)  {}



	login(username: string, pass: string): Observable<any> {
		const credentials = { username, pass };
		return this.http.post<any>("http://localhost:8080/user/login", credentials)
	}


	logout(): void {
		localStorage.removeItem("token");
		this.router.navigate(['/user/login']);
	}




  	isAuthenticated$(): Observable<boolean> {
  const token = localStorage.getItem('token');
  const isAuthenticated = token !== null && token !== undefined && token !== '';

  console.log("////////////////////////////");
  console.log(token);
  console.log("////////////////////////////");

  return of(isAuthenticated);
}

}