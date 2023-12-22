// crew-list.component.ts

import { Component, OnInit } from '@angular/core';
import { CrewService } from 'src/app/shared/services/crew/crew.service';
import { Router } from '@angular/router';
@Component({
  selector: 'app-crew-list',
  templateUrl: './crew-list.component.html',
})
export class CrewListComponent implements OnInit {

  crewList: any[] = [];

  constructor(private crewService: CrewService,private router: Router) {}

  ngOnInit() {
    this.getCrewList();
  }

  getCrewList() {
    this.crewService.getCrewList()
      .subscribe(
        data => {
          this.crewList = data;
        },
        error => {
          console.error('Error al obtener la lista de crew:', error);
        }
      );
  }
  
   editCrew(crewId: number) {
    // Almacena la ID del grupo que se va a editar
    console.log("*********************************************");
	console.log(crewId);
	console.log("*********************************************");
    this.crewService.setEditingCrewId(crewId);
    this.router.navigate(["/home/crew"]);
  
}}
