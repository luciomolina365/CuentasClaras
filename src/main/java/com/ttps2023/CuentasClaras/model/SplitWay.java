package com.ttps2023.CuentasClaras.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.DiscriminatorType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.OneToMany;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "split_way_type", discriminatorType = DiscriminatorType.STRING)
public abstract class SplitWay {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "splitway_id")
	private Long id;

	@Column(name = "name", unique = true)
	private String name;

	@OneToMany(mappedBy = "splitway", cascade = CascadeType.ALL)
	private List<Expense> expensesList;

	public SplitWay(String name) {
		super();
		this.name = name;
	}

	public SplitWay() {
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public List<Expense> getExpensesList() {
		return expensesList;
	}

	public void setExpensesList(List<Expense> expensesList) {
		this.expensesList = expensesList;
	}

	public abstract List<Payment> split(Expense expense, Crew crew);

	public abstract List<Payment> split();
}
