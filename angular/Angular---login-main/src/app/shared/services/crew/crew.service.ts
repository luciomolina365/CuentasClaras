import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Crew } from './crew';
import { Observable, EMPTY } from 'rxjs';

@Injectable({
	providedIn: 'root'
})
export class CrewService {
	private editingCrewId: number | null = null; //es mejor sessionStore?


	// private apiUrl = 'http://localhost:8080/crews'; // Reemplaza con la URL de tu API

	constructor(private http: HttpClient) { }

	createCrew(crewData: Crew): Observable<Crew> {
		return this.http.post<Crew>("http://localhost:8080/crew/create", crewData)

	}

	getCrewList(): Observable<any[]> {
		const idString = localStorage.getItem("id");

		if (idString) {
			const id: number = +idString;

			const url = `http://localhost:8080/user/${id}/crewList`;
			return this.http.get<any[]>(url);
		} else {
			console.error('El valor de id en localStorage es nulo o indefinido.');
			return EMPTY;
		}
	}

editCrew(crewId: number, crewData: any): Observable<any> {
  const id: number = +crewId;
  const url = `http://localhost:8080/crew/${id}/update`;
  crewData.category= +crewData.category;
  
  console.log(crewData)

  return this.http.put(url, crewData);
}



	setEditingCrewId(crewId: number | null) {
		this.editingCrewId = crewId;
	}

	getEditingCrewId(): number | null {
		return this.editingCrewId;
	}
	
	getCrew(crewId: number): Observable<Crew> {
	const id: number = +crewId;
	
    const url = `http://localhost:8080/crew/${id}`;  // Ajusta la URL según tu API
    return this.http.get<Crew>(url);
  }
	
	
}






