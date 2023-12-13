package com.ttps2023.CuentasClaras.repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ttps2023.CuentasClaras.model.ExpenseCategory;

public interface ExpenseCategoryRepository extends JpaRepository<ExpenseCategory, Long> {
	public Optional<ExpenseCategory> findById();
}