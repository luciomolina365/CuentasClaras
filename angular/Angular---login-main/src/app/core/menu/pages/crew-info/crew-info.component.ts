// crew-info.component.ts

import { Component, OnInit } from '@angular/core';
import { CrewService } from 'src/app/shared/services/crew/crew.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-crew-info',
  templateUrl: './crew-info.component.html',
})
export class CrewInfoComponent implements OnInit {

  crewData: any; 

  constructor(private crewService: CrewService, private route: ActivatedRoute) {
	  this.route.params.subscribe(params => {
	    const name = params['name'];

	    if (name) {
	    	
	      console.log('Crew name:', name);
	    }
	  });
	}
  ngOnInit() {

    const crewId = localStorage.getItem('crewId');

    if (crewId) {
    	const id: number = +crewId; 

      this.getCrewInfo(id);
    } else {
      console.error('CrewId no esta en localStorage.');
    }
  }

  getCrewInfo(crewId: number): void {
	  
    this.crewService.getCrew(crewId).subscribe(
      data => {
        this.crewData = data;
      },
      error => {
        console.error('Error al obtener el grupo: ', error);
      }
    );
  }
}