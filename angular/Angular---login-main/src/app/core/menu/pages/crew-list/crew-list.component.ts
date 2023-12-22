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

  constructor(private crewService: CrewService, private router: Router) {} 

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
          console.error('Error al obtener la lista de grupos: ', error);
        }
      );
  }
  
  onCrewItemClick(id: number, name: string): void {
	  
	  localStorage.setItem("crewId", id.toString());
	  
	  localStorage.setItem("crewName", name);

	    this.router.navigate(['/home/crew-info/', name]);
	  }
	  
  

   editCrew(crewId: number) {
    this.crewService.setEditingCrewId(crewId);
    this.router.navigate(["/home/crew"]);
  
	}
}
