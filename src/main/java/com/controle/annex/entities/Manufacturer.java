package com.controle.annex.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "FabricanteBike")
public class Manufacturer extends AbstractEntity<Long>{

    @Column(name = "nomeFabricante")
    @NotBlank(message = "Campo vazio. Insira um nome para o fabricante.")
    @NotNull(message = "Insira o nome do fabricante.")
    private String name;

    @JsonIgnore
    @OneToMany(mappedBy = "manufacturer")
    private List<Bike> bike;
}
