package com.controle.annex.controller;

import com.controle.annex.entities.Employee;
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
@RequestMapping("/employee")
public class EmployeeController {

    @Autowired
    private EmployeeService service;

    @GetMapping("/listEmployee")
    private ResponseEntity<List<Employee>> listaFuncionarios() {
        List<Employee> list = service.findAllEmployees();
        return ResponseEntity.ok().body(list);
    }

    @GetMapping("/findById/{id}")
    private ResponseEntity<Employee> buscarFuncionario(@Valid @PathVariable("id") Long id) {
        Optional<Employee> buscarFuncionario = service.findByIdEmployee(id);

        if (!buscarFuncionario.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(buscarFuncionario.get(), HttpStatus.OK);
    }

    @GetMapping("/findByName/{name}")
    private ResponseEntity<Employee> findEmployeeByName(@Valid @PathVariable("name") String name) {
        Optional<Employee> employee = service.findByName(name);
        if (!employee.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(employee.get(), HttpStatus.OK);

    }

    @PostMapping("/saveEmployee")
    private ResponseEntity<Employee> salveEmployee(@Valid @RequestBody Employee employee, BindingResult result) {

        if (result.hasErrors()) {
            result.getAllErrors();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        Employee save = service.saveEmployee(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.created(uri).build();


    }

    @PutMapping("/upateEmployee/{id}")
    private ResponseEntity<Employee> atualizarFuncionario(@Valid @PathVariable("id") Long id, @RequestBody Employee employee, BindingResult result) {
        Optional<Employee> findEmployee = service.findByIdEmployee(id);
        if (!findEmployee.isPresent()) {
            result.addError(new ObjectError("Employee", "Attention! Employee can't be change"));
        } else if (result.hasErrors()) {
            result.getAllErrors();
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        employee.setId(findEmployee.get().getId());
        Employee update = service.updateEmployee(employee);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(employee.getId()).toUri();
        return ResponseEntity.accepted().build();
    }

    @DeleteMapping("/deleteEmployee/{id}")
    private ResponseEntity<Employee> deleteFuncionario(@Valid @PathVariable("id") Long id) {

        service.deleteByIdEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}