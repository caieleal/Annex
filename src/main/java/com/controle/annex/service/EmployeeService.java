package com.controle.annex.service;

import com.controle.annex.entities.Employee;

import java.util.List;
import java.util.Optional;

public interface EmployeeService {

    Employee saveEmployee(Employee employee);

    Employee updateEmployee(Employee employee);

    Optional<Employee> findByName(String nome);

    Optional<Employee> findByIdEmployee(Long id);

    Employee deleteByIdEmployee(Long id);

    List<Employee> findAllEmployees();
}
