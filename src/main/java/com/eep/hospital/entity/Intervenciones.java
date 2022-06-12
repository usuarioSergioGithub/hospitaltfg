package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "intervenciones")
@NoArgsConstructor
public class Intervenciones {

    @Id
    @Column (name = "fecha", nullable = false)
    private LocalDateTime fecha;

    @Column (name = "descripcion", nullable = false)
    private String descripcion;

    @Column (name = "precio", nullable = false)
    private double precio;

    // ================ RELACIONES ================

    // relacion de la intervencion con la relacion ternaria
    @OneToMany(mappedBy = "intervenciones", orphanRemoval = true)
    Set<ClienteIntervDoctor> relacion = new HashSet<>();

    // relacion de la intervencion con las facturas
    @OneToMany(mappedBy = "fecha", orphanRemoval = true)
    Set<Facturas> facturas;

}
