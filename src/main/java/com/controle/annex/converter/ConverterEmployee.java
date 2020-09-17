package com.controle.annex.converter;

import com.controle.annex.dto.EmployeeDTO;
import com.controle.annex.entities.Employee;

public class ConverterEmployee {

    protected Employee employeeDtoToEmployee(EmployeeDTO dto){
        Employee employee = new Employee();
        employee.setId(dto.getId());
        employee.setName(dto.getName());
        employee.setEmail(dto.getEmail());
        employee.setCpf(dto.getCpf());
        employee.setFone(dto.getFone());
        return employee;
    }
    protected EmployeeDTO employeeToEmployeeDto(Employee employee){
        EmployeeDTO dto = new EmployeeDTO();
        dto.setId(employee.getId());
        dto.setName(employee.getName());
        dto.setCpf(employee.getCpf());
        dto.setEmail(employee.getEmail());
        dto.setFone(employee.getFone());
        return dto;
    }
}
