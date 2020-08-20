package com.controle.annex.entities;

import com.controle.annex.util.TypeEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.time.LocalDate;

@AllArgsConstructor
@NoArgsConstructor
@Data
@Entity
@Table(name = "OrdemDeServico")
public class WorkOrder extends AbstractEntity<Long>{

    @NotNull(message = "A descrição não pode ser nula.")
    @NotBlank(message = "Digite o serviço que será realizado.")
    @Column(name = "descricao")
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private TypeEnum type;

    @NotNull
    @Column(name = "valor", nullable = false, columnDefinition = "DECIMAL(6,2) DEFAULT 0.000")
    private BigDecimal value;

    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @NotNull
    @Column(name = "dataEntrada", columnDefinition = "DATE")
    private LocalDate inputDate;

    @NotNull
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE)
    @Column(name = "dataSaida", columnDefinition = "DATE")
    private LocalDate outputDate;



}
