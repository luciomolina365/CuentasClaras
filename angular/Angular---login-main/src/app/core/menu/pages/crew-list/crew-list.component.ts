// crew-list.component.ts

import { Component, OnInit } from '@angular/core';
import { CrewService } from 'src/app/shared/services/crew/crew.service';

@Component({
  selector: 'app-crew-list',
  templateUrl: './crew-list.component.html',
})
export class CrewListComponent implements OnInit {

  crewList: any[] = [];

  constructor(private crewService: CrewService) {}

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
}
