package com.ttps2023.CuentasClaras.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("EqualPerMember")
public class EqualPerMember extends SplitWay{

	public EqualPerMember(String name) {
		super(name);
	}
	
	public EqualPerMember() {
		super();
	}

	public List<Payment> split() {
    	return new ArrayList<Payment>();
	}
	
	
    public List<Payment> split(Expense expense, Crew crew) { //@valid
        Float amount = expense.getAmount();

        List<User> membersList = crew.getMembersList();
        
        if (membersList == null || membersList.isEmpty() || amount <= 0) { //DATOS invalidos
            return new ArrayList<>();	//o exception?
        }
        
        Float amountPerMember = amount / membersList.size();
        Date currentDate = new Date();  

        List<Payment> payments = new ArrayList<>();
        for (User member : membersList) {
            Payment payment = new Payment();
            payment.setDone(false);
            payment.setAmount(amountPerMember);
            payment.setDate(currentDate);
            payment.setBelongsTo(member);
            payments.add(payment);
//            member.setYouOwe(member.getYouOwe() + amountPerMember); //Actualizar youOwe de cada member?
        }        
//        User owner = expense.getBelongsTo();
//        owner.setYouAreOwed(owner.getYouAreOwed() + (amount - amountPerMember));  //Actualizar youAreOwed del dueño de expense?        
        return payments;
    }
}
