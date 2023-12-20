import { HttpClient, HttpErrorResponse } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { LoginRequest } from './loginRequest';
import { Observable, throwError, catchError, BehaviorSubject, tap, map } from 'rxjs';
import { User } from './user';
import { environment } from 'src/environments/environment';

@Injectable({
	providedIn: 'root'
})
export class LoginService {

	currentUserLoginOn: BehaviorSubject<boolean> = new BehaviorSubject<boolean>(false);
	currentUserData: BehaviorSubject<String> = new BehaviorSubject<String>("");

	constructor(private http: HttpClient) {
		this.currentUserLoginOn = new BehaviorSubject<boolean>(sessionStorage.getItem("token") != null);
		this.currentUserData = new BehaviorSubject<String>(sessionStorage.getItem("token") || "");
	}



	login(username: string, pass: string): Observable<any> {
		const credentials = { username, pass };
		return this.http.post<any>("http://localhost:8080/user/login", credentials)
	}


	logout(): void {
		sessionStorage.removeItem("token");
		this.currentUserLoginOn.next(false);
	}

	private handleError(error: HttpErrorResponse) {
		if (error.status === 0) {
			console.error('Se ha producio un error ', error.error);
		}
		else {
			console.error('Backend retornó el código de estado ', error);
		}
		return throwError(() => new Error('Algo falló. Por favor intente nuevamente.'));
	}

	get userData(): Observable<String> {
		return this.currentUserData.asObservable();
	}

	get userLoginOn(): Observable<boolean> {
		return this.currentUserLoginOn.asObservable();
	}

	get userToken(): String {
		return this.currentUserData.value;
	}

}