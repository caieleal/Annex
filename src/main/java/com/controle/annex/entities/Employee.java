package com.controle.annex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Data
@Table(name = "funcionario")
@AllArgsConstructor
@NoArgsConstructor
public class Employee extends AbstractEntity<Long> {


    @NotNull(message = "Informe um nome.")
    @NotBlank
    @Size(min = 3, max = 60, message = "O nome deve ter entra {min} e {max} caracteres.")
    @Column(name = "nomeFuncionario", unique = true, length = 60)
    private String nome;

    @NotBlank
    @NotNull(message = "Informe um CPF válido.")
    @Size(max = 14, message = "Verifique o CPF.")
    @Column(name = "cpfFuncionario")
    private String cpf;

    @NotBlank
    @NotNull(message = "Insira um número de telefone.")
    @Column(name = "telefoneFuncionario")
    private String telefone;

    @NotNull
    @NotBlank
    @Column(name = "emailFuncionario")
    @Email(message = "Insira um email válido.")
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Client> clients;


}
