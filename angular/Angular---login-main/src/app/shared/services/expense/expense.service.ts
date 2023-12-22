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
	  const idString = sessionStorage.getItem("expenseId");

		if (idString) {
			const id: number = +idString;

			return this.http.get<any[]>("http://localhost:8080/crew/${id}/expenses");
		} else {
			console.error('El valor de id en localStorage es nulo o indefinido.');
			return EMPTY;
		}
	}
  

  getExpense(expenseId: number): Observable<any> {
    return this.http.get<any>(`${this.baseUrl}/${expenseId}`);
  }

  createExpense(expenseData: any): Observable<any> {
	  const id: number = +expenseData.crewId;
    return this.http.post(`'http://localhost:8080/crew/${id}/expense`, expenseData);
  }

  editExpense(expenseId: number, expenseData: any): Observable<any> {
	  const id: number = +expenseId;
    return this.http.post(`http://localhost:8080/crew/expense/${id}`, expenseData);
  }

  setEditingExpenseId(expenseId: number | null): void {
    this.editingExpenseId = expenseId;
  }

  getEditingExpenseId(): number | null {
    return this.editingExpenseId;
  }
}