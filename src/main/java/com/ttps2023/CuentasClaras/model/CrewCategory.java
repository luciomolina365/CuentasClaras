package com.ttps2023.CuentasClaras.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "crewCategory")
public class CrewCategory {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@Column(name = "image")
	private String image;

	@OneToMany(mappedBy = "category", cascade = CascadeType.ALL)
	private List<Crew> crews;

	public CrewCategory(String name, String image) {
		this.name = name;
		this.image = image;
	}

	public CrewCategory() {
	}

	@Override
	public String toString() {
		return "CrewCategory [id=" + id + ", name=" + name + ", image=" + image + "]";
	}

}
