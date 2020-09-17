package com.controle.annex.service;

import com.controle.annex.entities.Employee;
import com.controle.annex.repository.EmployeeRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final Logger log = LoggerFactory.getLogger(EmployeeServiceImpl.class);

    @Autowired
    public EmployeeRepository repository;


    @Override
    public Employee saveEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Employee updateEmployee(Employee employee) {
        return repository.save(employee);
    }

    @Override
    public Optional<Employee> findByName(String name) {
        return repository.findByNameIgnoreCase(name);
    }

    @Override
    public Optional<Employee> findByIdEmployee(Long id) {
        return repository.findById(id);
    }

    @Override
    public Optional<Employee> findByCpf(String cpf) {
        return repository.findByCpf(cpf);
    }

    @Override
    public Optional<Employee> findByEmail(String email) {
        return repository.findByEmailIgnoreCase(email);
    }

    @Override
    public Employee deleteByIdEmployee(Long id) {
        Optional<Employee> funcionario = repository.findById(id);
        if (funcionario.isPresent()) {
            repository.deleteById(id);
        }
        return null;
    }

    @Override
    public List<Employee> findAllEmployees() {
        return repository.findAll();
    }
}
