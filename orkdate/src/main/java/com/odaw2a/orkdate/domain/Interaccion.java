package com.odaw2a.orkdate.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class Interaccion {
    @Id
    @GeneratedValue
    private Long id;

    @ManyToOne
    Usuario usuarioA;

    @ManyToOne
    Usuario usuarioB;

    private TipoInteraccion tipo;
}