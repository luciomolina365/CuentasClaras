package com.ttps2023.CuentasClaras.model;


import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

//	public void split() {
//		
//	}
	
	
    public List<Payment> split(Expense expense, List<User> membersList) {
        Float amount = expense.getAmount();

        if (membersList == null || membersList.isEmpty() || amount <= 0) {
            return new ArrayList<>();	//O exception?
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
//            payment.setExpense(expense);  
            payments.add(payment);


//            member.setYouOwe(member.getYouOwe() + amountPerMember); //Actualizar youOwe de cada member?
        }

       
        
//        User owner = expense.getBelongsTo();
//        owner.setYouAreOwed(owner.getYouAreOwed() + (amount - amountPerMember));  //Actualizar youAreOwed del dueño de expense?
        
        return payments;
    }
}
