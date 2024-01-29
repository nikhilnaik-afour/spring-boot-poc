package com.poc.dellnxppoc.db.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poc.dellnxppoc.db.dto.ClientJpaDTO;
import com.poc.dellnxppoc.db.model.Client;
import com.poc.dellnxppoc.db.repository.ClientJPARepository;

@Service("jpaService")
public class ClientJPAService implements ClientService {

	@Autowired
	private ClientJPARepository clientRepository;

	@Override
	public List<Client> getClients() {
		List<Client> clientList = new ArrayList<>();
		List<ClientJpaDTO> clientDtoObjs = clientRepository.findAll();
		clientDtoObjs.stream().forEach(obj -> {
			Client client = new Client();
			BeanUtils.copyProperties(obj, client);
			clientList.add(client);
		});
		return clientList;
	}

	@Override
	public void saveClient(Client client) {
		ClientJpaDTO clientDtoObj = new ClientJpaDTO();
		BeanUtils.copyProperties(client, clientDtoObj);
		clientRepository.save(clientDtoObj);
	}

	@Override
	public void deleteClientById(String id) {
		clientRepository.deleteById(id);
	}

}
