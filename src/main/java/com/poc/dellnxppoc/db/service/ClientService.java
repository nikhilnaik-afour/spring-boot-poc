package com.poc.dellnxppoc.db.service;

import java.util.List;

import com.poc.dellnxppoc.db.dto.Client;

public interface ClientService {
	List<Client> getClients();

	void saveClient(Client client);

	void deleteClientById(String id);
}
