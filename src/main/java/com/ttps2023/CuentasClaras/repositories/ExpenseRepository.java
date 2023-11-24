package com.ttps2023.CuentasClaras.repositories;


import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.Expense;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
	
}
