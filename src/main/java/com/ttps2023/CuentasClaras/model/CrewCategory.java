package com.ttps2023.CuentasClaras.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

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
	@JsonProperty(access = Access.WRITE_ONLY)
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@Column(name = "name")
	private String name;

	@JsonProperty(access = Access.WRITE_ONLY)
	@Column(name = "image")
	private String image;

	@JsonProperty(access = Access.WRITE_ONLY)
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public List<Crew> getCrews() {
		return crews;
	}

	public void setCrews(List<Crew> crews) {
		this.crews = crews;
	}

}
