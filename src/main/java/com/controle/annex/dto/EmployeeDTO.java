package com.controle.annex.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.br.CPF;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class EmployeeDTO {

    private Long id;

    @NotEmpty(message = "Nome não pode ser vazio.")
    @Size(min = 3, max = 200, message = "O nome deve conter entre 3 e 200 caracteres.")
    private String name;

    @NotEmpty(message = "O CPF não pode ser vazio.")
    @CPF(message = "Insira um cpf válido.")
    private String cpf;

    @NotEmpty(message = "O número não pode ser vazio.")
    private String fone;

    @NotEmpty(message = "O email não pode ser vazio.")
    @Size(min = 5, max = 200, message = "Insira um email entre 5 e 200 caracteres.")
    @Email(message = "Insira um email válido.")
    private String email;

    @Override
    public String toString() {
        return "EmployeeDTO{" +
                "id=" + id +
                ", nome='" + name + '\'' +
                ", cpf='" + cpf + '\'' +
                ", fone='" + fone + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
