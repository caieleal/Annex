package com.controle.annex.service;

import com.controle.annex.entities.Client;
import com.controle.annex.entities.Employee;
import com.controle.annex.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ClientServiceImpl implements ClientService {

    @Autowired
    private ClientRepository repository;

    @Override
    public Client saveClient(Client client) {
        client.setId(null);
        return repository.save(client);
    }

    @Override
    public Client updateClient(Client client) {
        return repository.save(client);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByNameClient(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Client> findByIdClient(Long id) {
        return repository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Client> findAllClients(Client clientes) {
        return repository.findAll();
    }

    @Override
    public Client deleteClient(Long id) {
        Optional<Client> client = repository.findById(id);
        if (client.isPresent()) {
            repository.deleteById(id);
        }
        return null;
    }

}
