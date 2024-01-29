package com.poc.dellnxppoc.db.service;

import com.poc.dellnxppoc.db.model.Client;
import org.springframework.stereotype.Service;

import java.util.List;


public interface ClientService {
    List<Client> getClients();

    void saveClient(Client client);

    void deleteClientById(String id);
}
