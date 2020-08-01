package com.controle.annex.service;

import com.controle.annex.entities.Employee;
import com.controle.annex.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

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
    @Transactional(readOnly = true)
    public Optional<Employee> findByName(String nome) {
        return repository.findByNomeIgnoreCase(nome);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Employee> findByIdEmployee(Long id) {
        return repository.findById(id);
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
