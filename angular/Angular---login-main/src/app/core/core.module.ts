// core.module.ts

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { CoreRoutingModule } from './core-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CrewComponent } from './menu/pages/crew/crew.component';

@NgModule({
	declarations: [MenuComponent, CrewComponent],
	imports: [CommonModule,
		FormsModule, CoreRoutingModule, ReactiveFormsModule],
	exports: [MenuComponent,
	],
})
export class CoreModule { }