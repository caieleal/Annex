package com.controle.annex.controller;

import com.controle.annex.entities.Client;
import com.controle.annex.entities.Employee;
import com.controle.annex.repository.EmployeeRepository;
import com.controle.annex.service.ClientService;
import com.controle.annex.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/client")
public class ClienteController {

    @Autowired
    private ClientService service;

    @Autowired
    private EmployeeService employeeService;

    @GetMapping("/findClient/{id}")
    private ResponseEntity<Client> findClientById(Client client) {
        Optional<Client> findClient = service.findByIdClient(client.getId());
        if (findClient.isPresent()) {
            return new ResponseEntity<>(findClient.get(), HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @GetMapping("/findClients")
    private ResponseEntity<List<Client>> buscarClientes(Client clientes) {
        List<Client> findAll = service.findAllClients(clientes);
        return ResponseEntity.ok().body(findAll);
    }


    @PostMapping("/saveClient/{employee}")
    private ResponseEntity<Client> salvarCliente(@PathVariable("employee") Long employee, @Valid @RequestBody Client client) {
        Optional<Employee> findEmployee = employeeService.findByIdEmployee(employee);
        if (findEmployee.isPresent()) {
            client.setEmployee(findEmployee.get());
            service.saveClient(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{employee}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.created(uri).build();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @PutMapping("/updateClient/{code}")
    private ResponseEntity<Client> updateClient(@PathVariable("code") Long code, @Valid @RequestBody Client client, BindingResult result) {
        Optional<Client> findClient = service.findByIdClient(code);
        if (!findClient.isPresent()) {
            result.addError(new ObjectError("Client", "Client can't be change!"));
        } else if (result.hasErrors()) {
            result.getAllErrors();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
            client.setId(findClient.get().getId());
            service.updateClient(client);
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{code}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.accepted().build();
    }
    @DeleteMapping("/deleteCliente/{code}")
    private ResponseEntity<Client> deleteCliente(@PathVariable("code")Long code, Client client){
        Optional<Client> findClient = service.findByIdClient(code);
        if(findClient.isPresent()){
            client.setId(findClient.get().getId());
            service.deleteClient(client.getId());
            URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("{code}").buildAndExpand(client.getId()).toUri();
            return ResponseEntity.accepted().build();
        }
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }
}


