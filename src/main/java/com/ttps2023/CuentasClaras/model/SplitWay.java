package com.ttps2023.CuentasClaras.model;

import java.util.List;

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
import jakarta.persistence.ManyToOne;


@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(discriminatorType = DiscriminatorType.STRING, name = "splitWay_type")
public abstract class SplitWay {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@ManyToOne
    private Long id;
	
	@Column(name = "name", unique = true)
	private String name;
	
	
	@OneToMany(mappedBy = "splitway")
	private List<Expense> expensesList;
	
	
	public SplitWay(String name) {
		super();
		this.name = name;
	}
	
	public SplitWay() {
	}
	

//	public abstract List<Payment> split();
	
}
