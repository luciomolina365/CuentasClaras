package com.ttps2023.CuentasClaras.model;


import java.util.Date;
import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;



@Entity
@Table(name="expense")
public class Expense {

	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "isPaid")
	private Boolean isPaid;
	
	@Column(name = "amount")
	private Float amount;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	private User belongsTo;
	
	@ManyToOne
	private Crew crew;
	
	@ManyToOne
	private ExpenseCategory category;
	
	@OneToMany(mappedBy = "expense", fetch = FetchType.LAZY)
	private List<Payment> paymentList;
	
//	private SplitWay splitway;
	
	
	
	
	public  Expense() {
		
	}

	public Expense(User belongsTo, Crew crew, Float amount, ExpenseCategory category, Date date, Boolean isPaid,
			List<Payment> paymentList) {
		super();
		this.belongsTo = belongsTo;
		this.crew = crew;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.isPaid = isPaid;
		this.paymentList = paymentList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Boolean getIsPaid() {
		return isPaid;
	}

	public void setIsPaid(Boolean isPaid) {
		this.isPaid = isPaid;
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

	public Crew getCrew() {
		return crew;
	}

	public void setCrew(Crew crew) {
		this.crew = crew;
	}

	public ExpenseCategory getCategory() {
		return category;
	}

	public void setCategory(ExpenseCategory category) {
		this.category = category;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}
	
	
	
	
	
	
	
	
	
}
