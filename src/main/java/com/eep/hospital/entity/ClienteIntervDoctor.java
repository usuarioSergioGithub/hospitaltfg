package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Getter
@Setter
@Entity
@Table(name = "cliente_interv_doctor")
@NoArgsConstructor
public class ClienteIntervDoctor {

    /*
    //establecer una clave primeria compuesta, es decir,
    se puede repetir cada uno por separado pero lo que no se
    puede repetir es el conjunto
     */
    @EmbeddedId
    private Relacion relacion;

    @Embeddable // especificar la tabla embebida
    @NoArgsConstructor
    @AllArgsConstructor
    public static class Relacion implements Serializable {

        /*
        Estos 3 campos forman una clave primaria compuesta
         */
        @Column(nullable = false)
        private String cliente_dni;

        @Column(nullable = false)
        private String doctor_ncoleg;

        @Column(nullable = false)
        private LocalDateTime fecha_interv;

    }

    // Relaciones a cada tabla
    //@MapsId("x") -> especificar el campo de la tabla de relación por el cual va a ser relacionado
    // *Especifico para relaciones de este tipo*
    @ManyToOne (cascade= CascadeType.REMOVE)
    @MapsId("cliente_dni")
    private ClienteEntity cliente;

    @ManyToOne(cascade= CascadeType.REMOVE)
    @MapsId("doctor_ncoleg")
    private Personal personal;

    @ManyToOne(cascade= CascadeType.REMOVE)
    @MapsId("fecha_interv")
    private Intervenciones intervenciones;

}
