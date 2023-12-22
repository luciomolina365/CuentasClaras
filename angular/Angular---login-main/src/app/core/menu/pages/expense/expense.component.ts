// expense.component.ts
import { Component, OnInit } from '@angular/core';
import { ExpenseService } from 'src/app/shared/services/expense/expense.service';
import { Router } from '@angular/router';

@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
})
export class ExpenseComponent implements OnInit {

  expenseData: any = {//setear los category, belongs to , crew id y splitway
    name: '',
    amount: null,
    splitwayId: null,
    categoryId: null
  };
  editingExpenseId: number | null = null;
  existingExpense: any | null = null;

  constructor(private expenseService: ExpenseService, private router: Router) {}

  ngOnInit() {
    this.editingExpenseId = this.expenseService.getEditingExpenseId();

    if (this.editingExpenseId !== null) {
      this.expenseService.getExpense(this.editingExpenseId).subscribe(
        (existingExpense: any) => {
          this.existingExpense = existingExpense;

          this.expenseData = {
            name: existingExpense.name,
            amount: existingExpense.amount,
            splitwayId: existingExpense.splitwayId,
            categoryId: existingExpense.categoryId
          };
        },
        (error: any) => {
          console.error('Error al obtener la información del gasto existente:', error);
        }
      );
    }
  }

  createOrEditExpense() {
    if (this.editingExpenseId !== null) {
      const expenseData = {
        name: this.expenseData.name,
        amount: this.expenseData.amount,
        splitwayId: this.expenseData.splitwayId,
        categoryId: this.expenseData.categoryId
      };

      this.expenseService.editExpense(this.editingExpenseId, expenseData)
        .subscribe(
         (response: any) => {
            console.log('Gasto editado con éxito:', response);
            this.resetForm();
          },
          (error: any) => {
            console.error('Error al editar el gasto:', error);
          }
        );
    } else {
      this.expenseService.createExpense(this.expenseData)
        .subscribe(
          (response: any) => {
            console.log('Gasto creado con éxito:', response);
            this.resetForm();
          },
          (error: any) => {
            console.error('Error al crear el gasto:', error);
          }
        );
    }

    this.expenseService.setEditingExpenseId(null);
    this.router.navigate(['/home/expenseList']);
  }

  resetForm() {
    this.expenseData = {
      name: '',
      amount: null,
      splitwayId: null,
      categoryId: null
    };
    this.editingExpenseId = null;
  }
}
