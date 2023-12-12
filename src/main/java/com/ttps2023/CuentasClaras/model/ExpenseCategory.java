package com.ttps2023.CuentasClaras.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "expenseCategory")
public class ExpenseCategory {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "image")
	private String image;
	
	
	@OneToMany(mappedBy = "category") 
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Expense> expenses;

	public ExpenseCategory(String category,String image) {
		this.name = category;
		this.image = image;
	}
	public ExpenseCategory() {}
	
}
