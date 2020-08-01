package com.controle.annex.service;

import com.controle.annex.entities.Client;

import java.util.List;
import java.util.Optional;

public interface ClientService {

    Client saveClient(Client client);

    Client updateClient(Client client);

    Optional<Client> findByNameClient(String name);

    Optional<Client> findByIdClient(Long id);

    List<Client> findAllClients(Client clientes);

    Client deleteClient(Long id);
}
