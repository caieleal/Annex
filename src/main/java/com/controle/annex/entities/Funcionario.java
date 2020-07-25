package com.controle.annex.entities;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Data
@Table(name = "funcionario")
public class Funcionario extends AbstractEntity<Long> {

    @NotNull(message = "Informe um nome.")
    @NotBlank
    @Size(min = 3, max = 60, message= "O nome deve ter entra {min} e {max} caracteres.")
    @Column(name = "nomeFuncionario", nullable = false, unique = true, length = 60)
    private String nome;

    @NotBlank
    @NotNull(message = "Informe um CPF válido.")
    @Size(min = 11, max = 11, message = "Verifique o CPF.")
    @Column(name = "cpfFuncionario", nullable = false)
    private String cpf;

    @NotBlank
    @NotNull(message = "Insira um número de telefone.")
    @Column(name = "telefoneFuncionario", nullable = false)
    private String telefone;

    @NotNull
    @NotBlank
    @Column(name = "emailFuncionario",nullable = false)
    @Email(message = "Insira um email válido.")
    private String email;

}
