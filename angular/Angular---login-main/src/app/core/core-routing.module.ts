import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { HomeComponent } from './menu/pages/home/home.component';
import {CrewComponent } from './menu/pages/crew/crew.component';
import {MenuComponent } from './menu/menu.component';


const routes: Routes = [
  {
    path: '',
    children: [
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

