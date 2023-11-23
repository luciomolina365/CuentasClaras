package com.ttps2023.CuentasClaras.model;

import java.util.List;
import jakarta.persistence.*;



@Entity
@Table(name="user")
public class User {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
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
	private String pass;

	@Column(name = "youOwe")
	private Float youOwe ;
	
	@Column(name = "youAreOwed")
	private Float youAreOwed;

	@OneToMany(mappedBy = "user") 
	private List<Contacts> contacts;
	

	@ManyToMany
	@JoinTable(
			name="user_crew",
			joinColumns = {@JoinColumn(name="id_user")},
			inverseJoinColumns = {@JoinColumn(name="id_crew")})
	private List<Crew> crewList;
	
	
	@OneToMany(mappedBy = "user") //, cascade = CascadeType.REMOVE agrego cascade¿?
	private List<FriendRequest> friendRequestList;
	
	@OneToMany(mappedBy = "belongsTo") 
	private List<Payment> paymentList;
	
	@OneToMany(mappedBy = "belongsTo") 
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
		this.contacts = null;
		this.crewList = null;
		this.friendRequestList = null;
		this.expenseList= null;
		this.paymentList=null;

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
	
	
	
	
//	public void addFriend(User user) {
//		this.friendsList.add(user);
//		user.friendsList.add(this);
//		//acordarse de agregarlo en la lista del otro usuario
//	}

	public List<Contacts> getContacts() {
		return contacts;
	}

	public void setContacts(List<Contacts> contacts) {
		this.contacts = contacts;
	}

	public void addGroup() {}

	public void addPrivate() {}

	@Override
	public String toString() {
		return "User [username=" + username + ", lastname=" + lastname + ", name=" + name + ", email=" + email
				+ ", pass=" + pass + ", youOwe=" + youOwe + ", youAreOwed=" + youAreOwed + ", crewList=" + crewList
				+ ", friendRequestList=" + friendRequestList + ", paymentList=" + paymentList + ", expenseList="
				+ expenseList + "]";
	}

}
