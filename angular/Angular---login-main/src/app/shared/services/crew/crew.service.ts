import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';
import { Crew } from './crew';

@Injectable({
  providedIn: 'root'
})
export class CrewService {

 // private apiUrl = 'http://localhost:8080/crews'; // Reemplaza con la URL de tu API

  constructor(private http: HttpClient) { }

  createCrew(crewData: Crew): Observable<Crew> {
    return this.http.post<Crew>("http://localhost:8080/crew/create", crewData)    
    
  }
  

  getCrewList(): Observable<any[]> {
    return this.http.get<any[]>(this.apiUrl);
  }
}

  
  
  
}
