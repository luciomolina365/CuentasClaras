import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { CrewComponent } from './menu/pages/crew/crew.component';
import { MenuComponent } from './menu/menu.component';
import { CrewListComponent } from './menu/pages/crew-list/crew-list.component';
import { CrewInfoComponent } from './menu/pages/crew-info/crew-info.component';
import { ExpenseComponent } from './menu/pages/expense/expense.component';
import { ExpenseEditComponent } from './menu/pages/expense-edit/expense-edit.component';

const routes: Routes = [
  {
    path: 'crew-info/:name',
    component: CrewInfoComponent
  },
  {
    path: 'crew-info/:name/:expenseName/edit',
    component: ExpenseEditComponent
  },
  {
    path: 'crew-info/:name/expense',
    component: ExpenseComponent
  },
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
];

@NgModule({
  imports: [RouterModule.forChild(routes)],
  exports: [RouterModule]
})
export class CoreRoutingModule {}
