// expense.service.ts
import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable, EMPTY } from 'rxjs';

@Injectable({
  providedIn: 'root',
})
export class ExpenseService {
  private baseUrl = 'http://localhost:8080/crew'; // Ajusta la URL base según tu configuración

  private editingExpenseId: number | null = null;

  constructor(private http: HttpClient) {}

  getExpenseList(): Observable<any[]> {
    const idString = localStorage.getItem("crewId");

    if (idString) {
      const id: number = +idString;
      return this.http.get<any[]>(`${this.baseUrl}/${id}/expenses`);
    } else {
      console.error('El valor de id en localStorage es nulo o indefinido.');
      return EMPTY;
    }
  }

  getExpense(expenseId: number): Observable<any> {
	  const expenseEditId=localStorage.getItem("expenseId")
	  
	  
	  if (expenseEditId) {
			const id: number = +expenseEditId;
	  
	  
	  	const idEx:number=+expenseEditId
	 
	  
    return this.http.get<any>(`${this.baseUrl}/expense/${idEx}`);
    } else {
			console.error('El valor de id en localStorage es nulo o indefinido.');
			return EMPTY;
		}
	}
    

  saveOrUpdateExpense(expenseData: any): Observable<any> {
	  
	  const isUpdate = expenseData.expenseId != null;


	  let url: string;

	  let requestMethod: string;
	  if (isUpdate) {

	    url = `${this.baseUrl}/expense/${expenseData.expenseId}`;
	    requestMethod = 'PUT';
	  } else {

	    url = `${this.baseUrl}/${expenseData.crewId}/expense`;
	    requestMethod = 'POST';
	  }

	  // casting a number
	  expenseData.belongsToId = expenseData.belongsToId != null ? +expenseData.belongsToId : null;
	  expenseData.amount = expenseData.amount != null ? +expenseData.amount : null;
	  expenseData.splitwayId = expenseData.splitwayId != null ? +expenseData.splitwayId : null;
	  expenseData.crewId = expenseData.crewId != null ? +expenseData.crewId : null;
	  expenseData.categoryId = expenseData.categoryId != null ? +expenseData.categoryId : null;

	  return this.http.request(requestMethod, url, { body: expenseData });
	}


  setEditingExpenseId(expenseId: number | null): void {
    this.editingExpenseId = expenseId;
  }

  getEditingExpenseId(): number | null {
    return this.editingExpenseId;
  }
}
