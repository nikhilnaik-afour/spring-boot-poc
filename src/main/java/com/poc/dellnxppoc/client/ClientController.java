package com.poc.dellnxppoc.client;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/v1/client")
public class ClientController {

	private final ClientService clientService;

	@Autowired
	public ClientController(ClientService clientService) {
		super();
		this.clientService = clientService;
	}

	@GetMapping
	public List<Client> getClients() {
		return clientService.getClients();
	}
	
}
