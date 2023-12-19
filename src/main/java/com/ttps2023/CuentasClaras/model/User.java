package com.ttps2023.CuentasClaras.model;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@JsonProperty(access = Access.WRITE_ONLY)
	private Long id;

	@Column(unique = true, name = "username")
	private String username;

	@Column(name = "lastname")
	private String lastname;

	@Column(name = "name")
	private String name;

	@Column(unique = true, name = "email")
	private String email;

	@Column(name = "pass")
	@JsonProperty(access = Access.WRITE_ONLY)
	private String pass;

	@Column(name = "youOwe")
	private Float youOwe;

	@Column(name = "youAreOwed")
	private Float youAreOwed;

	@OneToMany(mappedBy = "user")
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Contact> contactList;

	@ManyToMany
	@JsonProperty(access = Access.WRITE_ONLY)
	@JoinTable(name = "user_crew", joinColumns = { @JoinColumn(name = "id_user") }, inverseJoinColumns = {
			@JoinColumn(name = "id_crew") })
	private List<Crew> crewList;

	@OneToMany(mappedBy = "user") 
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<FriendRequest> friendRequestList;

	@OneToMany(mappedBy = "belongsTo", cascade = CascadeType.ALL)
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Payment> paymentList;

	@OneToMany(mappedBy = "belongsTo", cascade = CascadeType.ALL)
	@JsonProperty(access = Access.WRITE_ONLY)
	private List<Expense> expenseList;

	public User(String username, String lastname, String name, String email, String pass) {
		super();
		this.username = username;
		this.lastname = lastname;
		this.name = name;
		this.email = email;
		this.pass = pass;
		this.youOwe = (float) 0;
		this.youAreOwed = (float) 0;
		this.contactList = null;
		this.crewList = null;
		this.friendRequestList = null;
		this.expenseList = null;
		this.paymentList = null;

	}

	public User() {
		super();
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getLastname() {
		return lastname;
	}

	public void setLastname(String lastname) {
		this.lastname = lastname;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPass() {
		return pass;
	}

	public void setPass(String pass) {
		this.pass = pass;
	}

	public Float getYouOwe() {
		return youOwe;
	}

	public void setYouOwe(Float youOwe) {
		this.youOwe = youOwe;
	}

	public Float getYouAreOwed() {
		return youAreOwed;
	}

	public void setYouAreOwed(Float youAreOwed) {
		this.youAreOwed = youAreOwed;
	}

	public List<Contact> getContacts() {
		return contactList;
	}

	public void setContacts(List<Contact> contacts) {
		this.contactList = contacts;
	}

	public List<Contact> getContactList() {
		return contactList;
	}

	public void setContactList(List<Contact> contactList) {
		this.contactList = contactList;
	}

	public List<Crew> getCrewList() {
		return crewList;
	}

	public void setCrewList(List<Crew> crewList) {
		this.crewList = crewList;
	}

	public List<FriendRequest> getFriendRequestList() {
		return friendRequestList;
	}

	public void setFriendRequestList(List<FriendRequest> friendRequestList) {
		this.friendRequestList = friendRequestList;
	}

	public List<Payment> getPaymentList() {
		return paymentList;
	}

	public void setPaymentList(List<Payment> paymentList) {
		this.paymentList = paymentList;
	}

	public List<Expense> getExpenseList() {
		return expenseList;
	}

	public void setExpenseList(List<Expense> expenseList) {
		this.expenseList = expenseList;
	}
	

	@Override
	public String toString() {
		return "User [username=" + username + ", lastname=" + lastname + ", name=" + name + ", email=" + email
				+ ", pass=" + pass + ", youOwe=" + youOwe + ", youAreOwed=" + youAreOwed + ", crewList=" + crewList
				+ ", friendRequestList=" + friendRequestList + ", paymentList=" + paymentList + ", expenseList="
				+ expenseList + "]";
	}

	public void addContact(User user) {

		LocalDate today = LocalDate.now();

		Date date = Date.valueOf(today);

		Contact contact1 = new Contact(this, date);
		Contact contact2 = new Contact(user, date);

		this.contactList.add(contact1);
		this.contactList.add(contact2);

	}

	public void addToYouAreOwed(float amount) {
		youAreOwed += amount;
	}

	public void subtractFromYouAreOwed(float amount) {
		youAreOwed -= amount;
	}

	public void addToYouOwe(float amount) {
		youOwe += amount;
	}

	public void subtractFromYouOwe(float amount) {
		youOwe -= amount;
	}

}
