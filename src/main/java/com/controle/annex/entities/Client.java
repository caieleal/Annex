package com.controle.annex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.Cascade;

import javax.persistence.*;
import javax.validation.Valid;
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
    @Size(max = 15)
    @Column(name = "telefoneCliente")
    private String fone;

    @NotNull
    @Size(max = 14)
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
    private Employee employee;

    @OneToOne(mappedBy = "client")
    @JsonIgnore
    private Bike bike;
}
