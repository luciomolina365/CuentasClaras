// expense-list.component.ts
import { Component, OnInit } from '@angular/core';
import { ExpenseService } from 'src/app/shared/services/expense/expense.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expense-list',
  templateUrl: './expense-list.component.html',
})
export class ExpenseListComponent implements OnInit {

  expenseList: any[] = [];

  constructor(private expenseService: ExpenseService, private router: Router) {}

  ngOnInit() {
    this.getExpenseList();
  }

  getExpenseList() {
    this.expenseService.getExpenseList()
      .subscribe(
        data => {
          this.expenseList = data;
        },
        error => {
          console.error('Error al obtener la lista de gastos:', error);
        }
      );
  }


  editExpense(expenseId: number, expenseName: string) {
	  // Almacena la ID del gasto que se va a editar
	  const crewName = localStorage.getItem("crewName");
	  localStorage.setItem("expenseId", expenseId.toString());

	  // Navegar a la ruta correspondiente con el nombre del gasto y el nombre del gasto específico
	  this.router.navigate(['/home/crew-info', crewName, expenseName, 'edit']);
	}


  
  
  onCrewItemClick(id: number, name: string): void {
	  
	  localStorage.setItem("crewId", id.toString())

	    this.router.navigate(['/home/crew-info/', name]);
	  }
  
  goToExpenseComponent() {
	  console.log(Object.keys(localStorage));
	  const name = localStorage.getItem("crewName");
	  this.router.navigate([`/home/crew-info/${name}/expense`]);
	}

  
  
}
