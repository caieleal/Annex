package com.controle.annex.service;

import com.controle.annex.entities.Employee;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    @Transactional(readOnly = true)
    Optional<Employee> findByName(String name);

    @Transactional(readOnly = true)
    Optional<Employee> findByIdEmployee(Long id);

    @Transactional(readOnly = true)
    Optional<Employee> findByCpf(String cpf);

    @Transactional(readOnly = true)
    Optional<Employee> findByEmail(String email);

    Employee deleteByIdEmployee(Long id);

    List<Employee> findAllEmployees();
}
