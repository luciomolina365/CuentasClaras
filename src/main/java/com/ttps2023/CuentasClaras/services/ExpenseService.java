package com.ttps2023.CuentasClaras.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.Crew;
import com.ttps2023.CuentasClaras.model.Expense;
import com.ttps2023.CuentasClaras.repositories.ExpenseRepository;


@Service
public class ExpenseService {

	
	
	private final ExpenseRepository expenseRepository;
	
	
	   public ExpenseService(ExpenseRepository expenseRepository) {
	        this.expenseRepository = expenseRepository;
	    }
	
	   
	   
		public void create(Expense expense) {
			expenseRepository.save(expense);
		}

		public Optional<Expense> getById(Long id) {
			return expenseRepository.findById(id);
		}
	
		public Expense updateExpense(Long expenseId, Date date)  {
			

			Optional<Expense> expenseQuery = expenseRepository.findById(expenseId);
			Expense expenseBd = expenseQuery.orElse(null);
			
			expenseBd.setDate(date);
			
			return expenseRepository.save(expenseBd);
			
		
			
		}
		
		
}
