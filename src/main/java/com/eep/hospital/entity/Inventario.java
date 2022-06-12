package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table (name = "inventario")
@NoArgsConstructor
public class Inventario {

    @Id
    @Column(name = "modelo")
    private String modelo;

    @Column(name = "tipoArticulo")
    private String tipoArticulo;

    @Column(name = "descripcion")
    private String descripcion;

    @Column(name = "fabricante")
    private String fabricante;

    @Column(name = "fechaCompra")
    private LocalDate fechaCompra;

    @Column(name = "precioArticulo")
    private double precioArticulo;

    @Column(name = "unidades")
    private int unidades;


    // Varios articulos pueden ser de la misma especialidad
    @ManyToOne
    @JoinColumn (name = "nombreEspecialidad")
    EspecialidadEntity especialidad;


}