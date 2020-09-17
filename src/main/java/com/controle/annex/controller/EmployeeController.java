package com.controle.annex.controller;

import com.controle.annex.dto.EmployeeDTO;
import com.controle.annex.entities.Employee;
import com.controle.annex.service.EmployeeService;
import com.controle.annex.response.Response;
import com.controle.annex.validation.EmployeeValidation;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;


@RestController
@RequestMapping("/annex")
public class EmployeeController extends EmployeeValidation {

    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);

    @Autowired
    private EmployeeService service;

    @GetMapping("/listarFuncionarios")
    private ResponseEntity<List<Employee>> listaFuncionarios() {
        List<Employee> employees = service.findAllEmployees();

        return ResponseEntity.ok().body(employees);
    }

    @GetMapping("/func/{id}")
    private ResponseEntity<Response<EmployeeDTO>> buscandoFuncionario(@PathVariable("id") Long id) {
        log.info("Buscando funcionário com o id: {}", id );
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByIdEmployee(id);

        if(!employee.isPresent()){
            log.info("Funcionário com o id: {}", id + " não encontrado.");
            response.getErrors().add("Funcionáriro não encontrado para o id: "+id);
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.employeeToEmployeeDto(employee.get()));
        return ResponseEntity.ok().body(response);
    }

    @GetMapping("/funcNome/{name}")
    private ResponseEntity<Response<EmployeeDTO>> buscandoPorNome(@PathVariable("name") String name){
        log.info("Buscando funcionário com o nome: {}", name);
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByName(name);

        if(!employee.isPresent()){
            log.info("Funcionário com o nome: ", name + " não encontrado.");
            response.getErrors().add("Funcionário com o nome: "+ name + " não existe.");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.employeeToEmployeeDto(employee.get()));
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/funcEmail/{email}")
    private ResponseEntity<Response<EmployeeDTO>> buscandoPorEmail(@PathVariable("email")String email){
        log.info("Buscando funcionário com o email: {}", email);
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByEmail(email);

        if(!employee.isPresent()){
            log.info("Funcionário com o email: {}", email + " não encontrado.");
            response.getErrors().add("Funcionário com o email: "+ email+ " não encontrado");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.employeeToEmployeeDto(employee.get()));
        return ResponseEntity.ok().body(response);
    }
    @GetMapping("/funcCpf/{cpf}")
    private ResponseEntity<Response<EmployeeDTO>> buscandoPorCpf(@PathVariable("cpf") String cpf){
        log.info("Buscando funcionário com o cpf: {}", cpf);
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByCpf(cpf);

        if(!employee.isPresent()){
            log.info("Funcionário com o cpf: {}", cpf +" não encontrado.");
            response.getErrors().add("Funcionário com o cpf: "+cpf+" não encontrado.");
            return ResponseEntity.badRequest().body(response);
        }
        response.setData(this.employeeToEmployeeDto(employee.get()));
        return ResponseEntity.ok().body(response);
    }

    @PostMapping("/criarFuncionario")
    private ResponseEntity<Response<EmployeeDTO>> salveEmployee(@Valid @RequestBody EmployeeDTO dto, BindingResult result) {
        log.info("Cadastranndo o funcionario: {}", dto.toString());
        Response<EmployeeDTO> response = new Response<>();

        employeeValidation(dto, result);

        Employee employee = this.employeeDtoToEmployee(dto);

        if(result.hasErrors()){
            log.error("Erro de validação de funcionário: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        service.saveEmployee(employee);
        response.setData(this.employeeToEmployeeDto(employee));
        return ResponseEntity.ok(response);

    }

    @PutMapping("/atualiFunc/{id}")
    private ResponseEntity<Response<EmployeeDTO>> atualizarFuncionario(@PathVariable("id") Long id, @Valid @RequestBody EmployeeDTO dto, BindingResult result){
        log.info("Atualizando funcionário : {}", id);
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByIdEmployee(id);

        if(!employee.isPresent()){
            result.addError(new ObjectError("funcionário", "funcionário não encontrado."));
        }
        this.employeeValidationUpdate(employee.get(), dto, result);
        if(result.hasErrors()){
            log.error("Erro de validação: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }
        this.service.updateEmployee(employee.get());
        response.setData(this.employeeToEmployeeDto(employee.get()));
        return ResponseEntity.ok().body(response);
    }

    @DeleteMapping("/deleteEmployee/{id}")
    private ResponseEntity<Response<EmployeeDTO>> deleteFuncionario(@PathVariable("id") Long id, EmployeeDTO dto, BindingResult result) {
        log.info("Deletando funcionnario no id: {}", id);
        Response<EmployeeDTO> response = new Response<>();
        Optional<Employee> employee = service.findByIdEmployee(id);

        if(!employee.isPresent()){
            result.addError(new ObjectError("funcionário", "funcionário não encontrado."));
        }
        if(result.hasErrors()){
            log.error("Erro de validação: {}", result.getAllErrors());
            result.getAllErrors().forEach(error -> response.getErrors().add(error.getDefaultMessage()));
            return ResponseEntity.badRequest().body(response);
        }

        service.deleteByIdEmployee(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}