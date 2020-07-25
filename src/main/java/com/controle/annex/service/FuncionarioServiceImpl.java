package com.controle.annex.service;

import com.controle.annex.entities.Funcionario;
import com.controle.annex.repository.FuncionarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FuncionarioServiceImpl implements FuncionarioService {

    @Autowired
    public FuncionarioRepository repository;


    @Override
    public Funcionario salvarFuncionario(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @Override
    public Funcionario atualizarFuncionario(Funcionario funcionario) {
        return repository.save(funcionario);
    }

    @Override
    public Optional<Funcionario> buscarPorNome(String nome) {
        return repository.findByNomeEquals(nome);
    }

    @Override
    public Optional<Funcionario> buscarPorId(Long id) {
        return repository.findById(id);
    }

    @Override
    public Funcionario deletePorId(Long id) {
        Optional<Funcionario> funcionario = repository.findById(id);
        if(funcionario.isPresent()){
            repository.deleteById(id);
        }
        return null;
    }

    @Override
    public List<Funcionario> buscarTodos(Funcionario funcionario) {
        return repository.findAll();
    }
}
