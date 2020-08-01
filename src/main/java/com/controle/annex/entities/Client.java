package com.controle.annex.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class Client extends AbstractEntity<Long> {


    @NotBlank
    @Size(min = 3, max = 60)
    @NotNull(message = "Digite um nome.")
    @Column(name = "nomeCliente", unique = true)
    private String name;

    @NotNull
    @NotBlank(message = "Digite o número de telefone do cliente.")
    @Size(max = 11)
    @Column(name = "telefoneCliente")
    private String fone;

    @NotNull
    @Size(max = 11)
    @NotBlank(message = "Digite um cpf válido.")
    @Column(name = "cpfCliente", unique = true)
    private String cpf;

    @Email(message = "Digite um email válido.")
    @NotBlank(message = "Este campo não pode ser vazio.")
    @NotNull
    @Column(name = "emailCliente")
    private String email;

    @ManyToOne
    @JoinColumn(name = "funcionario_id", referencedColumnName = "id")
    @NotNull(message = "Escolha um funcionário.")
    private Employee employee;

}
