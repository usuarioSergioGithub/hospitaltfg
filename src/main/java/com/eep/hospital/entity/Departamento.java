package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table (name = "departamento")
@NoArgsConstructor
@AllArgsConstructor
public class Departamento {

    @Id
    @Column (name = "nombre")
    private String nombre;

    // ================ RELACIONES ================

    // En departamento guardo la clave foránea del centro
    // Muchos departamentos tiene el centro
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (referencedColumnName = "nombre")
    Centro centro;
}
