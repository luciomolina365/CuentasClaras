package com.ttps2023.CuentasClaras.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;


@Entity
@Table(name="crew")
public class Crew {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "isPrivate")
	private Boolean isPrivate;
	
	@ManyToOne
	private CrewCategory category;
 	
 	@ManyToMany(mappedBy = "crewList", cascade = CascadeType.ALL)
 	private List<User> membersList;
 	 	
 	@OneToMany(mappedBy = "crew", cascade = CascadeType.ALL)
	private List<Expense> expenseList;
 	
	public Crew(String groupName, Boolean isPrivate, CrewCategory category, List<User> membersList) {
		super();
		this.name = groupName;
		this.active = true;
		this.isPrivate = isPrivate;
		this.category = category;
		this.membersList = membersList;
		this.expenseList = null;
	}
	
	public Crew() {}

	

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public Boolean getIsPrivate() {
		return isPrivate;
	}

	public void setIsPrivate(Boolean isPrivate) {
		this.isPrivate = isPrivate;
	}

	public CrewCategory getCategory() {
		return category;
	}

	public void setCategory(CrewCategory category) {
		this.category = category;
	}

	public List<User> getMembersList() {
		return membersList;
	}

	public void setMembersList(List<User> membersList) {
		this.membersList = membersList;
	}

	public List<Expense> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<Expense> expenseList) {
		this.expenseList = expenseList;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	public void addExpense(Expense expense) {
		
		this.expenseList.add(expense);
	}
 	
}
