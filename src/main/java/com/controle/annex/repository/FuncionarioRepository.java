package com.controle.annex.repository;

import com.controle.annex.entities.Funcionario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;


public interface FuncionarioRepository extends JpaRepository<Funcionario, Long> {

    Optional<Funcionario> findByNomeEquals(String nome);
}
