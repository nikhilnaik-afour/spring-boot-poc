package com.poc.dellnxppoc.client;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
public class ClientService {

	@Autowired
	private ClientRepository clientRepository;

	public List<Client> getClients() {
		return clientRepository.findAll();
	}

	public void saveClient(Client client) {
		clientRepository.save(client);
	}

	public void deleteClientById(String id) {
		clientRepository.deleteById(id);
	}

}
