import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';


import {CrewComponent } from './menu/pages/crew/crew.component';
import {MenuComponent } from './menu/menu.component';
import {CrewListComponent } from './menu/pages/crew-list/crew-list.component';


const routes: Routes = [
  {
    path: '',
    children: [
		 {
        path: 'crewList',
        component: CrewListComponent
      },
	 {
        path: 'crew',
        component: CrewComponent
      },
       {
        path: 'menu',
        component: MenuComponent
      },
      {
        path: '**',
        redirectTo: 'menu'
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
export class CoreRoutingModule { }

