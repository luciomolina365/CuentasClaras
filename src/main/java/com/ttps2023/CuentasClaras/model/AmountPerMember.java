package com.ttps2023.CuentasClaras.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("AmountPerMember")
public class AmountPerMember extends SplitWay {

	public AmountPerMember(String name) {
		super(name);
	}
	
	public AmountPerMember() {
		super();
	}
	
	@Override
	public List<Payment> split() {
    	return new ArrayList<Payment>();
	}
	public List<Payment> split(Expense expense, Crew crew) {
    	return new ArrayList<Payment>();
	}
}
