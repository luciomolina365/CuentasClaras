import { Component, OnInit } from '@angular/core';
import { User } from 'src/app/shared/services/auth/user';

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styles: [ `
    .container{
      margin: 20px;
    }
  `
  ]
})
export class HomeComponent implements OnInit {
  constructor() { }

  ngOnInit(): void {
  }

}