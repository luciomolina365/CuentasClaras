package com.ttps2023.CuentasClaras.model;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "expense")
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

	@Column(name = "name")
	private String name;

	@ManyToOne
	private User belongsTo;

	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private Crew crew;

	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	private ExpenseCategory category;

	@JsonProperty(access = Access.WRITE_ONLY)
	@OneToMany(mappedBy = "expense", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Payment> paymentList;

	@ManyToOne
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinColumn(name = "splitway_id")
	private SplitWay splitway;

	public Expense() {}

	public Expense(String name, User belongsTo, Crew crew, Float amount, ExpenseCategory category, Date date, Boolean isPaid,
			List<Payment> paymentList, SplitWay splitway) {
		this.belongsTo = belongsTo;
		this.crew = crew;
		this.amount = amount;
		this.category = category;
		this.date = date;
		this.isPaid = isPaid;
		this.paymentList = paymentList;
		this.splitway = splitway;
		this.name = name;
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

	public SplitWay getSplitway() {
		return splitway;
	}

	public void setSplitway(SplitWay splitway) {
		this.splitway = splitway;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
