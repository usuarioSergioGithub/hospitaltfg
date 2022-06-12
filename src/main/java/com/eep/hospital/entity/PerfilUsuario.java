package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table (name = "perfilUsuario")
@NoArgsConstructor
@AllArgsConstructor
public class PerfilUsuario {

    @Id
    @Column (name = "rol")
    private String rol;

}
