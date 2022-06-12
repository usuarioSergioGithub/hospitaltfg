package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Table (name = "receta")
@Getter
@Setter
@NoArgsConstructor
public class Receta {

    @Id
    @Column (name = "id")
    private Long id;

    @Column (name = "prescripcion")
    private String prescripcion;

    // ================ RELACIONES ================

    // Muchas recetas pueden ser escritas por la misma persona
    // En recetas se guarda la clave foránea de personal
    @ManyToOne // Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (referencedColumnName = "dniNColegiado")
    private Personal personal;// datos del personal que escribio la receta

    // Muchas recetas pueden tener el mismo paciente
    // En recetas se guarda la clave foránea del cliente
    @ManyToOne // Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (referencedColumnName = "dni")
    private ClienteEntity cliente;

}
