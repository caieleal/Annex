package com.controle.annex.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "Fabricante_bike")
public class Manufacturer extends AbstractEntity<Long>{

    @Column(name = "nome_fabricante")
    @NotBlank(message = "Campo vazio. Insira um nome para o fabricante.")
    @NotNull(message = "Insira o nome do fabricante.")
    private String name;

    @OneToMany(mappedBy = "manufacturer")
    private List<Bike> bike;
}
