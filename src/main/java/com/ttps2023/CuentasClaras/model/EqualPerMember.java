package com.ttps2023.CuentasClaras.model;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@DiscriminatorValue("EqualPerMember")
public class EqualPerMember extends SplitWay{

	public EqualPerMember(String name) {
		super(name);
	}
	
	public EqualPerMember() {
		super();
	}

	public void split() {
		
	}
}
