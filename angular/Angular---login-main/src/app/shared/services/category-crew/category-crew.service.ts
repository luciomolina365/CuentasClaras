import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CategoryCrewService {

  private apiUrl = 'http://localhost:8080/categories-crew'; // Reemplaza con la URL de tu API

  constructor(private http: HttpClient) { }

  getCategories(): Observable<string[]> {
    return this.http.get<string[]>(this.apiUrl);
  }
}
