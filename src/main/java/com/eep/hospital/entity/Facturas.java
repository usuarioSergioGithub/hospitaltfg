package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "facturas")
public class Facturas {

    @Id
    @Column(name = "fecha")
    private LocalDateTime fecha;

    @Column(name = "pagada")
    private boolean pagada;

    // ================ RELACIONES ================

    // Varias facturas puede tener el mismo cliente
    // En facturas se guarda la clave foranea del cliente
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "dni")
    private ClienteEntity cliente;

    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "dniNColegiado")
    private Personal personal;

    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "fecha_intervencion")
    private Intervenciones intervenciones;
}
