package com.controle.annex.controller;

import com.controle.annex.entities.Funcionario;
import com.controle.annex.service.FuncionarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/funcionario")
public class FuncionarioController {

    @Autowired
    private FuncionarioService service;

    @GetMapping("/listarFuncionarios")
    private List<Funcionario> listaFuncionarios(Funcionario funcionarios) {

        return service.buscarTodos(funcionarios);

    }

    @GetMapping("/buscarFuncionario/{id}")
    private ResponseEntity<Funcionario> buscarFuncionario(@Valid @PathVariable("id") Long id) {
        Optional<Funcionario> funcionario = service.buscarPorId(id);

        if (!funcionario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(funcionario.get(), HttpStatus.OK);
    }

    @PostMapping("/salvarFuncionario")
    private ResponseEntity<Funcionario> salvarFuncionario(@Valid @RequestBody Funcionario funcionario) {

        service.salvarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.CREATED).body(funcionario);

    }

    @PutMapping("/atualizarFuncionario/{id}")
    private ResponseEntity<Funcionario> atualizarFuncionario(@Valid @PathVariable("id") Long id, @RequestBody Funcionario funcionario, BindingResult result) {
        Optional<Funcionario> buscarFuncionario = service.buscarPorId(id);
        if (buscarFuncionario.isPresent()) {
            funcionario.setId(buscarFuncionario.get().getId());
            if (result.hasErrors()) {
                return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
            }
        }
        service.atualizarFuncionario(funcionario);
        return ResponseEntity.status(HttpStatus.OK).body(funcionario);
    }

    @DeleteMapping("/excluirFuncionario/{id}")
    private ResponseEntity<Funcionario> deleteFuncionario(@Valid @PathVariable("id") Long id) {

        service.deletePorId(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}