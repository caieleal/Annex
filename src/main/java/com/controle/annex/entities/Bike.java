package com.controle.annex.entities;

import lombok.Data;

import javax.persistence.*;
import javax.validation.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
@Entity
@Table(name = "bicicletas")
public class Bike extends AbstractEntity<Long> {


    @NotNull(message = "Insira um tamanho.")
    @NotBlank(message = "Atencao, campo nao pode ser vazio.")
    @Column(name = "tamanho_bike")
    private Integer size;

    @NotNull(message = "Atencao, a cor nao pode ser nula.")
    @NotBlank(message = "Insira uma cor.")
    @Column(name = "cor_bike")
    private String color;

    @Column(name = "modelo_bike")
    @NotNull(message = "Insira um modelo.")
    @NotBlank(message = "Campo vazio. Insira o modelo.")
    private String model;

    @NotNull(message = "Escolha um fabricante.")
    @ManyToOne
    @JoinColumn(name = "fabricante_id", referencedColumnName = "id")
    private Manufacturer manufacturer;

}
