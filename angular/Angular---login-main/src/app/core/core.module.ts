import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { MenuRoutingModule } from './core-routing.module';

import { HomeComponent } from './menu/pages/home/home.component';

import {CrewComponent } from './menu/pages/crew/crew.component';
import { MenuComponent } from './menu/menu.component';

@NgModule({
  declarations: [
    HomeComponent,
    CrewComponent,
    MenuComponent
  ],
  imports: [
    CommonModule,
    MenuRoutingModule,
    FormsModule
  ]
})
export class MenuModule { }