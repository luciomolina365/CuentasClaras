package com.ttps2023.CuentasClaras.services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.ttps2023.CuentasClaras.model.ExpenseCategory;
import com.ttps2023.CuentasClaras.repositories.ExpenseCategoryRepository;
import com.ttps2023.CuentasClaras.repositories.FriendRequestRepository;




@Service
public class ExpenseCategoryService {

    private final ExpenseCategoryRepository expenseCategoryRepository;
    

    public ExpenseCategoryService(ExpenseCategoryRepository expenseCategoryRepository) {
        this.expenseCategoryRepository = expenseCategoryRepository;

    }
	
	public Optional<ExpenseCategory> getById(Long id) {
		return expenseCategoryRepository.findById(id);
	}
}
