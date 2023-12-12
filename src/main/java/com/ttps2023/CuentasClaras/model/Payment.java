package com.ttps2023.CuentasClaras.model;

import java.util.Date;

import jakarta.persistence.*;


@Entity
@Table(name="payment")
public class Payment {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "done")
	private Boolean done;
	
	@Column(name = "amount")
	private Float amount;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	private User belongsTo;
		
	@ManyToOne
//  @JoinColumn(name = "expense_id")
    private Expense expense;
	
	public Payment(Boolean done, Float amount, Date date, User belongsTo) {
		super();
		this.done = done;
		this.amount = amount;
		this.date = date;
		this.belongsTo = belongsTo;
	}

	public Payment() {}
	
	
	public Boolean getDone() {
		return done;
	}

	
	public void setDone(Boolean done) {
		this.done = done;
	}


	public Float getAmount() {
		return amount;
	}


	public void setAmount(Float amount) {
		this.amount = amount;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}


	public User getBelongsTo() {
		return belongsTo;
	}


	public void setBelongsTo(User belongsTo) {
		this.belongsTo = belongsTo;
	}
	
}
