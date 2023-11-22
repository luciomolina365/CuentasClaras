package com.ttps2023.CuentasClaras.model;


import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;



@Entity
@Table(name = "friendRequest")
public class FriendRequest {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name = "accepted")
	private Boolean accepted;
	
	@Column(name = "date")
	private Date date;
	
	@ManyToOne
	@JoinColumn(name="id_user")
	private User user;
	
	public FriendRequest(Boolean accepted, Date date, User user) {
		super();
		this.accepted = accepted;
		this.date = date;
		this.user = user;
	}
	
	public FriendRequest() {}
	
	public Boolean getAccepted() {
		return accepted;
	}
	public void setAccepted(Boolean accepted) {
		this.accepted = accepted;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	public User getUser() {
		return user;
	}
	public void setUser(User user) {
		this.user = user;
	}
	
}
