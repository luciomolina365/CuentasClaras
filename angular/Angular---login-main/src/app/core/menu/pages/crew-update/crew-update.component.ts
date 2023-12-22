// crew.component.ts

import { Component } from '@angular/core';
import { CrewService } from 'src/app/shared/services/crew/crew.service';

@Component({
  selector: 'app-crew',
  templateUrl: './crew-update.component.html',
})
export class CrewUpdateComponent {
  crewName: string = '';
  isPrivate: boolean = false;
  category: number = 4;

  // Agrega una propiedad para almacenar el ID de la tripulación que se está editando
  editingCrewId: number | null = null;

  constructor(private crewService: CrewService) {}

  createCrew() {
    // ... código para crear una tripulación
  }

  editCrew() {
    if (this.editingCrewId !== null) {
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
    }
  }

  // Agrega una función para resetear los campos del formulario después de editar
  resetForm() {
    this.crewName = '';
    this.isPrivate = false;
    this.category = 4;
    this.editingCrewId = null;
  }
}
