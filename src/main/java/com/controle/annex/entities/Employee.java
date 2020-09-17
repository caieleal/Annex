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


    @Column(name = "nomeFuncionario", nullable = false, unique = true, length = 60)
    private String name;

    @Column(name = "cpfFuncionario", nullable = false, unique = true)
    private String cpf;

    @Column(name = "telefoneFuncionario", nullable = false)
    private String fone;

    @Column(name = "emailFuncionario", nullable = false)
    private String email;

    @JsonIgnore
    @OneToMany(mappedBy = "employee")
    private List<Client> clients;


}
