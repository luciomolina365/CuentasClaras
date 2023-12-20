import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class CrewService {

 // private apiUrl = 'http://localhost:8080/crews'; // Reemplaza con la URL de tu API

  constructor(private http: HttpClient) { }

  createCrew(crewData: any): Observable<any> {
    return this.http.post<any>("http://localhost:8080/crew/create", crewData)
    
    
  }
}
