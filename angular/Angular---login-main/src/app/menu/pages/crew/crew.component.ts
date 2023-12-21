import { Component, OnInit } from '@angular/core';
//import { CategoryCrewService } from 'src/app/shared/services/category-crew/category-crew.service';
import { CrewService } from 'src/app/shared/services/crew/crew.service';


@Component({
 selector: 'app-crew',
  templateUrl: './crew.component.html',
})


export class CrewComponent {
//implements OnInit {

  crewName: string = '';
  isPrivate: boolean = false;
  category: string = '';
  

  constructor(
    private crewService: CrewService
  ) {}

  ngOnInit() {
  }

  createCrew() {
   	const token = localStorage.getItem('token');


    const crewData = {
      name: this.crewName,
      isPrivate: this.isPrivate,
      membersList: [1],
      category: this.category
    };

    this.crewService.createCrew(crewData)
      .subscribe(
        response => {
          console.log('Tripulación creada con éxito:', response);
          // Puedes realizar acciones adicionales después de la creación
        },
        error => {
          console.error('Error al crear la tripulación:', error);
        }
      );
  }
}
