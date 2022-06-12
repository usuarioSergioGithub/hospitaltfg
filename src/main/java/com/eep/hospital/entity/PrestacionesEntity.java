package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "prestaciones")
public class PrestacionesEntity {

    @Id
    @Column ( name = "prestacion")
    private String nombrePrestacion;

    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "especialidad")
    EspecialidadEntity especialidad;

    
}
