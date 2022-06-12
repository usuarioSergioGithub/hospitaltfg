package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter
@Setter
@Entity
@Table (name = "centro")
@NoArgsConstructor
@AllArgsConstructor
public class Centro {

    @Id
    @Column (name = "nombre")
    private String nombre;

    @Column (name = "direccion")
    private String direccion;

    @Column (name = "movil")
    private String movil;

    @Column (name = "telFijo")
    private String telFijo;

    @Column (name = "provincia")
    private String provincia;

    @Column (name = "horario")
    private String horario;

    @Column (name = "correo")
    private String correo;

    @Column (name = "cp")
    private int cp;

}
