package com.controle.annex.repository;

import com.controle.annex.entities.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Repository
@Transactional(readOnly = true)
public interface EmployeeRepository extends JpaRepository<Employee, Long> {

    Optional<Employee> findByNameIgnoreCase(String name);

    Optional<Employee> findByCpf(String cpf);

    Optional<Employee> findByEmailIgnoreCase(String email);
}
