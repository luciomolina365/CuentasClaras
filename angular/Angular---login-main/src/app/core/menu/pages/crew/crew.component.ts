import { Component, OnInit } from '@angular/core';
//import { CategoryCrewService } from 'src/app/shared/services/category-crew/category-crew.service';
import { CrewService } from 'src/app/shared/services/crew/crew.service';
import { Crew } from 'src/app/shared/services/crew/crew';

@Component({
	selector: 'app-crew',
	templateUrl: './crew.component.html',
})


export class CrewComponent {
	//implements OnInit {

	crewName: string = '';
	isPrivate: boolean = false;
	category: number = 4;


	constructor(
		private crewService: CrewService
	) { }

	ngOnInit() {
	}

	createCrew() {
		const token = localStorage.getItem('token');

		const crewData: Crew = {
			name: this.crewName,
			isPrivate: this.isPrivate,
			membersList: [Number(localStorage.getItem("id"))],
			category: Number(this.category)
		};

		this.crewService.createCrew(crewData)
			.subscribe(
				response => {
					console.log('Tripulación creada con éxito:', response);
				},
				error => {
					console.error('Error al crear la tripulación:', error);
				}
			);
	}
}
