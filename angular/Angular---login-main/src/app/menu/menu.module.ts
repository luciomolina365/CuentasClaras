import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MenuRoutingModule } from './menu-routing.module';

import { HomeComponent } from './pages/home/home.component';

import {CrewComponent } from './pages/crew/crew.component';
import { ExpenseComponent } from './pages/expense/expense.component';
import { ListCrewsComponent } from './pages/list-crews/list-crews.component';

@NgModule({
  declarations: [
    HomeComponent,
    CrewComponent,
    ExpenseComponent,
    ListCrewsComponent
  ],
  imports: [
    CommonModule,
    MenuRoutingModule,
    FormsModule
  ]
})
export class MenuModule { }