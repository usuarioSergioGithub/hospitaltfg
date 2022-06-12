package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name = "mensajes")
@NoArgsConstructor
@AllArgsConstructor
public class MensajesEntity {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;

    @Column (name = "fehaMensaje")
    private LocalDate fehaMensaje;

    @Column (name = "correo")
    private String correo;

    @Column (name = "nombre")
    private String nombre;

    @Column (name = "apellidos")
    private String apellidos;

    @Column (name = "mensaje")
    private String mensaje;


}
