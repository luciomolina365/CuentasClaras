import { Component, OnInit } from '@angular/core';
import { Router, ActivatedRoute } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { ExpenseService } from 'src/app/shared/services/expense/expense.service';

@Component({
  selector: 'app-expense-edit',
  templateUrl: './expense-edit.component.html',
  styleUrls: ['./expense-edit.component.scss']
})
export class ExpenseEditComponent implements OnInit {
  isEditMode: boolean = false;
  expenseForm: FormGroup;

  constructor(
    private route: ActivatedRoute,
    private fb: FormBuilder,
    private expenseService: ExpenseService
  ) {
    this.expenseForm = this.fb.group({
      name: ['', Validators.required],
      date: ['', Validators.required]
    });
  }

  ngOnInit() {
	  
	  console.log("EXPENSE EDIIIITTTT ")
    this.route.queryParams.subscribe(params => {
      this.isEditMode = params['edit'] === 'true';

      if (this.isEditMode) {
    	  
        const expenseId = params['expenseId'];


        this.expenseService.getExpense(expenseId).subscribe(
          (expenseDetails: any) => {
            this.expenseForm.setValue({
              name: expenseDetails.name,
              date: expenseDetails.date
            });
          },
          error => {
            console.error('Error al obtener los detalles del gasto', error);
          }
        );
      }
    });
  }
  
  
  
}

