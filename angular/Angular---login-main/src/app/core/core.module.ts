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
import { CrewUpdateComponent } from './menu/pages/crew-update/crew-update.component';
import { ExpenseListComponent } from './menu/pages/expense-list/expense-list.component';
import { ExpenseCreateComponent } from './menu/pages/expense-create/expense-create.component';
import { ExpenseComponent } from './menu/pages/expense/expense.component';

@NgModule({
	declarations: [MenuComponent, CrewComponent, CrewListComponent, CrewUpdateComponent, ExpenseListComponent, ExpenseCreateComponent, ExpenseComponent],
	imports: [CommonModule,
		FormsModule, CoreRoutingModule, ReactiveFormsModule],
	exports: [MenuComponent,
	],
	providers: [CrewService],
})
export class CoreModule { }