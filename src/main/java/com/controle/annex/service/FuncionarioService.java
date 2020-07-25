package com.controle.annex.service;

import com.controle.annex.entities.Funcionario;

import java.util.List;
import java.util.Optional;

public interface FuncionarioService {

    Funcionario salvarFuncionario(Funcionario funcionario);

    Funcionario atualizarFuncionario(Funcionario funcionario);

    Optional<Funcionario> buscarPorNome(String nome);

    Optional<Funcionario> buscarPorId(Long id);

    Funcionario deletePorId(Long id);

    List<Funcionario> buscarTodos(Funcionario funcionario);
}
