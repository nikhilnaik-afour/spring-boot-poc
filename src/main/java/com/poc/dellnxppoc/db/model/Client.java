package com.poc.dellnxppoc.db.model;

import java.time.LocalDate;

public class Client {

	private String id;
	private String name;
	private String location;
	private LocalDate initializationDate;

	public Client() {
		super();
	}

	public Client(String name, String location, LocalDate initializationDate) {
		super();
		this.name = name;
		this.location = location;
		this.initializationDate = initializationDate;
	}

	public Client(String id, String name, String location, LocalDate initializationDate) {
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
