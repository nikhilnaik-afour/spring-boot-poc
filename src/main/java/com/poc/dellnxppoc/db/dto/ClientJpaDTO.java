package com.poc.dellnxppoc.db.dto;

import java.time.LocalDate;

import org.hibernate.annotations.UuidGenerator;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;

@Entity
@Table(name = "clients")
public class ClientJpaDTO {
	
	@jakarta.persistence.Id
	@UuidGenerator
	private String id;
	
	@Column(name = "name")
	private String name;

	@Column(name = "location")
	private String location;

	@Column(name = "initializationDate")
	private LocalDate initializationDate;

	public ClientJpaDTO() {
		super();
	}

	public ClientJpaDTO(String name, String location, LocalDate initializationDate) {
		super();
		this.name = name;
		this.location = location;
		this.initializationDate = initializationDate;
	}

	public ClientJpaDTO(String id, String name, String location, LocalDate initializationDate) {
		super();
		this.id = id;
		this.name = name;
		this.location = location;
		this.initializationDate = initializationDate;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public LocalDate getInitialization_date() {
		return initializationDate;
	}

	public void setInitialization_date(LocalDate initializationDate) {
		this.initializationDate = initializationDate;
	}

}
