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
@Table(name = "contact")
public class Contacts {
		@Id
	    @GeneratedValue(strategy = GenerationType.IDENTITY)
	    private Long id;
		
		@Column(name = "youOwe")
		private Float youOwe ;
		
		@Column(name = "youAreOwed")
		private Float youAreOwed;
	
		@Column(name = "date")
		private Date date;
	
		@ManyToOne
		@JoinColumn(name="id_user")
		private User user;

		public Contacts(User user, Date date) {
			super();
			this.user = user;
			this.date = date;
			this.youOwe = (float) 0.0;
			this.youAreOwed = (float) 0.0;
		}

		
		
}
