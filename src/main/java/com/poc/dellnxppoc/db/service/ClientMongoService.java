package com.poc.dellnxppoc.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dellnxppoc.db.dto.Client;
import com.poc.dellnxppoc.db.model.ClientMongoEntity;
import com.poc.dellnxppoc.db.repository.ClientMongoRepository;

@Service("mongoService")
public class ClientMongoService implements ClientService {

	@Autowired
	private ClientMongoRepository clientRepository;

	public List<Client> getClients() {
		List<Client> clientList = new ArrayList<>();
		List<ClientMongoEntity> clientEntities = clientRepository.findAll();
		clientEntities.stream().forEach(obj -> {
			Client client = new Client();
			BeanUtils.copyProperties(obj, client);
			clientList.add(client);
		});
		return clientList;
	}

	public void saveClient(Client client) {
		ClientMongoEntity clientEntity = new ClientMongoEntity();
		BeanUtils.copyProperties(client, clientEntity);
		clientRepository.save(clientEntity);
	}

	public void deleteClientById(String id) {
		clientRepository.deleteById(id);
	}

}
