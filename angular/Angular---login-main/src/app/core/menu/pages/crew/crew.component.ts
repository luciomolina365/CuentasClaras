// crew.component.ts
import { Component, OnInit } from '@angular/core';
import { CrewService } from 'src/app/shared/services/crew/crew.service';
import { Crew } from 'src/app/shared/services/crew/crew';
import { Router } from '@angular/router';
@Component({
	selector: 'app-crew',
	templateUrl: './crew.component.html',
	//  styleUrls: ['./crew.component.scss']
})
export class CrewComponent implements OnInit {

	crewName: string = '';
	isPrivate: boolean = false;
	category: number = 4;
	editingCrewId: number | null = null;
	existingCrew: Crew | null = null;

	constructor(private crewService: CrewService,private router: Router) { }

	ngOnInit() {
		this.editingCrewId = this.crewService.getEditingCrewId();

		if (this.editingCrewId !== null) {
			// Si estamos en modo de edición, obtenemos la información de la tripulación existente
			this.crewService.getCrew(this.editingCrewId).subscribe(
				(existingCrew: Crew) => {
					this.existingCrew = existingCrew;

					// Asignamos los valores al formulario
					this.crewName = existingCrew.name;
					this.isPrivate = existingCrew.isPrivate;
					this.category = 4
				},
				(error: any) => {
					console.error('Error al obtener la información de la tripulación existente:', error);
				}
			);
		}
	}

	createOrEditCrew() {
		if (this.editingCrewId !== null) {
			// Lógica para editar un grupo existente
			const crewData = {
				name: this.crewName,
				isPrivate: this.isPrivate,
				category: this.category,
			};

			this.crewService.editCrew(this.editingCrewId, crewData)
				.subscribe(
					response => {
						console.log('Tripulación editada con éxito:', response);
						// Limpia los campos después de editar
						this.resetForm();
					},
					error => {
						console.error('Error al editar la tripulación:', error);
					}
				);
		} else {
			// Lógica para crear un nuevo grupo
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
						// Limpia los campos después de crear
						this.resetForm();
					},
					error => {
						console.error('Error al crear la tripulación:', error);
					}
				);
				
		}

		// Después de crear o editar, reinicia la variable de edición
		if (this.editingCrewId !== null) {
			this.crewService.setEditingCrewId(null);
		}
		this.router.navigate(['/home/crewList']);

	}
	getCategoryName(categoryNumber: any): string {
		// Verifica si el objeto tiene la propiedad 'name'
		if (categoryNumber && categoryNumber.name) {
			return categoryNumber.name;
		}

		// Si no tiene la propiedad 'name', utiliza un valor predeterminado
		return 'Desconocido';
	}
	// Función para resetear los campos del formulario después de crear o editar
	resetForm() {
		this.crewName = '';
		this.isPrivate = false;
		this.category = 4;
		this.editingCrewId = null;
	}
}

