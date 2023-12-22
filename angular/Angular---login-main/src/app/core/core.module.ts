// core.module.ts

import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { MenuComponent } from './menu/menu.component';
import { CoreRoutingModule } from './core-routing.module';
import { FormsModule } from '@angular/forms';
import { ReactiveFormsModule } from '@angular/forms';
import { CrewComponent } from './menu/pages/crew/crew.component';
import { CrewListComponent } from './menu/pages/crew-list/crew-list.component';
import { CrewService } from 'src/app/shared/services/crew/crew.service';

import { ExpenseListComponent } from './menu/pages/expense-list/expense-list.component';

import { ExpenseComponent } from './menu/pages/expense/expense.component';
import { CrewInfoComponent } from './menu/pages/crew-info/crew-info.component';
import { ExpenseEditComponent } from './menu/pages/expense-edit/expense-edit.component';


@NgModule({
	declarations: [MenuComponent, CrewComponent, CrewListComponent, CrewInfoComponent, ExpenseListComponent, ExpenseComponent, ExpenseEditComponent],
	imports: [CommonModule,
		FormsModule, CoreRoutingModule, ReactiveFormsModule],
	exports: [MenuComponent,
	],
	providers: [CrewService],
})
export class CoreModule { }