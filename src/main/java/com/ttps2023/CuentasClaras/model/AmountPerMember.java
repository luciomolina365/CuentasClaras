package com.ttps2023.CuentasClaras.model;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("AmountPerMember")
public class AmountPerMember extends SplitWay {

	public AmountPerMember(String name) {
		super(name);
	}
	
	public AmountPerMember() {
		super();
	}
	
	public void split() {
		
	}
}
