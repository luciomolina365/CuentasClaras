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

  editExpense(expenseId: number) {
    // Almacena la ID del gasto que se va a editar
    console.log("*********************************************");
    console.log(expenseId);
    console.log("*********************************************");
    this.expenseService.setEditingExpenseId(expenseId);
    this.router.navigate(["/home/expense"]);
  }
}
