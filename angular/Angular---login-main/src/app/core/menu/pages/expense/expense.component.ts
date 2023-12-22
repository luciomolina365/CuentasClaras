import { Component, OnInit } from '@angular/core';
import { ExpenseService } from 'src/app/shared/services/expense/expense.service';
import { Router } from '@angular/router';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';


@Component({
  selector: 'app-expense',
  templateUrl: './expense.component.html',
})
export class ExpenseComponent implements OnInit {
  expenseForm: FormGroup = this.fb.group({
    name: ['', Validators.required],
    belongsToId: [null],
    amount: [null, [Validators.required, Validators.min(0)]],
    splitwayId: [null, Validators.required],
    crewId: [null],
    categoryId: [null, Validators.required],
  });

  expenseData: any = {
    name: '',
    belongsToId: null,
    amount: null,
    splitwayId: null,
    crewId: null,
    categoryId: null,
  };
  

  constructor(
    private expenseService: ExpenseService,
    private router: Router,
    private fb: FormBuilder,
  ) {}

  ngOnInit() {
		
  }
  
  

  onSubmit() {
	  if (this.expenseForm.valid) {
	    const formData = this.expenseForm.value;
	    console.log(formData);

	    const crewId = localStorage.getItem('crewId');
	    if (crewId) {
	      const id: number = +crewId; 
	      formData.crewId = id;
	    } else {
	      console.error('CrewId no está en localStorage.');
	    }

	    const userId = localStorage.getItem('id');
	    if (userId) {
	      const id: number = +userId;
	      formData.belongsToId = id;
	    } else {
	      console.error('UserId no está en localStorage.');
	    }	

	    this.expenseService.saveOrUpdateExpense(formData).subscribe(
	      (response) => {
	        console.log('Gasto guardado correctamente:', response);
	        const name = localStorage.getItem("crewName");
	        this.router.navigate(['/home/crew-info/', name]);
	      },
	      (error) => {
	        console.error('Error al guardar el gasto:', error);
	      }
	    );
	  }
	}


}
