package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table (name = "usuarios")
public class UsuarioEntity {

    @Id
    @Column (name = "correoUsuario")
    private String correoUsuario;

    @Column (name = "password")
    private String password;

    // ================ RELACIONES ================

    // En personal guardo la clave foranea de perfil de usuario, es decir, el tipo de rol
    // Hay varias personas con el mismo rol
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "rol")
    PerfilUsuario perfilUsuario;



}
