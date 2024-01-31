package com.poc.dellnxppoc.db.model;

import java.time.LocalDate;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("clients")
public class ClientMongoEntity {
	@Id
	private String id;
	private String name;
	private String location;
	private LocalDate initializationDate;

	public ClientMongoEntity() {
		super();
	}

	public ClientMongoEntity(String name, String location, LocalDate initializationDate) {
		super();
		this.name = name;
		this.location = location;
		this.initializationDate = initializationDate;
	}

	public ClientMongoEntity(String id, String name, String location, LocalDate initializationDate) {
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

	public LocalDate getInitializationDate() {
		return initializationDate;
	}

	public void setInitializationDate(LocalDate initializationDate) {
		this.initializationDate = initializationDate;
	}

}
