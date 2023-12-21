import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class AuthService {
	private apiUrl = 'http://localhost:8080/user/create';

	constructor(private http: HttpClient) { }

	signup(userData: any): Observable<any> {
		return this.http.post(this.apiUrl, userData);
	}

}
