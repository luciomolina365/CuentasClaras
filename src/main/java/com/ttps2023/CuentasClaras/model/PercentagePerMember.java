package com.ttps2023.CuentasClaras.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("PercentagePerMember")
public class PercentagePerMember extends SplitWay {
	
	public PercentagePerMember(String name) {
		super(name);
	}
	
	public PercentagePerMember() {
	}
	
	public void split() {
		
	}
}
