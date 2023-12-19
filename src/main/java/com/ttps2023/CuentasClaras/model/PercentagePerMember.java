package com.ttps2023.CuentasClaras.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("PercentagePerMember")
public class PercentagePerMember extends SplitWay {
	
	public PercentagePerMember(String name) {
		super(name);
	}
	
	public PercentagePerMember() {
	}
	
	@Override
	public List<Payment> split() {
    	return new ArrayList<Payment>();
	}
	public List<Payment> split(Expense expense, Crew crew) {
    	return new ArrayList<Payment>();
	}
}
