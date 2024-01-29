package com.poc.dellnxppoc.db.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poc.dellnxppoc.db.model.Client;
import com.poc.dellnxppoc.db.service.ClientMongoService;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

	private final ClientMongoService clientService;

	@Autowired
	public ClientController(ClientMongoService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> getClients() {
		return clientService.getClients();
	}
	
}
