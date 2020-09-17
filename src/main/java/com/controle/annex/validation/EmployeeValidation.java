package com.controle.annex.validation;

import com.controle.annex.converter.ConverterEmployee;
import com.controle.annex.dto.EmployeeDTO;
import com.controle.annex.entities.Employee;
import com.controle.annex.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;

public class EmployeeValidation extends ConverterEmployee {

    @Autowired
    private EmployeeService service;

    protected void employeeValidation(EmployeeDTO dto, BindingResult result){
        service.findByName(dto.getName()).ifPresent(employee -> result.addError(new ObjectError("employee", "Funcionário: "+ dto.getName() +" ja existe.")));
        service.findByCpf(dto.getCpf()).ifPresent(employee -> result.addError(new ObjectError("employee", "Cpf: " + dto.getCpf() +" já cadastrado.")));
        service.findByEmail(dto.getEmail()).ifPresent(employee -> result.addError(new ObjectError("employee", "Email: "+ dto.getEmail()+" já cadastrado.")));

    }
    protected void employeeValidationUpdate(Employee employee, EmployeeDTO dto, BindingResult result){
        if(!employee.getEmail().equals(dto.getEmail())){
            this.service.findByEmail(dto.getEmail())
                    .ifPresent(emp -> result.addError(new ObjectError("employee", "Email: "+dto.getEmail()+" já está em uso.")));
                    employee.setEmail(dto.getEmail());
        }

    }
}
