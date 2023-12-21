import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './pages/home/home.component';
import {CrewComponent } from './pages/crew/crew.component';


const routes: Routes = [
  {
    path: '',
    children: [
	 {
        path: 'crew',
        component: CrewComponent
      },
       {
        path: 'home',
        component: HomeComponent
      },
      {
        path: '**',
        redirectTo: 'home'
      }
    ]
  }
];

@NgModule({  
  imports: [
    RouterModule.forChild( routes )
  ],
  exports: [
    RouterModule
  ]
})
export class MenuRoutingModule { }

