import { Component, OnInit } from '@angular/core';
import { CategoryCrewService } from 'src/app/shared/services/category-crew/category-crew.service';
import { CrewService } from 'src/app/shared/services/crew/crew.service';
import * as jwt_decode from "jwt-decode";


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

if (token) {
  try {
    // Decodificar el token JWT
    const decodedToken: any = jwt_decode(token);

    // Acceder al campo del nombre de usuario
    const username = decodedToken.sub;

    console.log('Nombre de usuario:', username);
  } catch (error) {
    console.error('Error al decodificar el token:', error);
  }
} else {
  console.error('No se encontró el token en el localStorage');
}




    const crewData = {
      name: this.crewName,
      isPrivate: this.isPrivate,
      membersList: [userId],
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
