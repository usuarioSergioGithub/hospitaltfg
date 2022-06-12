package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.NotFound;
import org.hibernate.annotations.NotFoundAction;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@Entity
@Table(name = "cita")
@NoArgsConstructor
@AllArgsConstructor
public class Cita {

    @Id
    @GeneratedValue
    @Column (name = "id")
    private Long id;

    @Column (name = "hora")
    private LocalTime hora;

    @Column (name = "fechaDisponible")
    private LocalDate fechaDisponible;


    // ================ RELACIONES ================

    // Varias citas pueden ser pedidas por el mismo cliente
    // En cita se guarda la clave foranea del cliente
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "dni")
    private ClienteEntity clientePaciente;

    // Varias citas pueden ser pedidas por el mismo cliente
    // En cita se guarda la clave foranea del cliente
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @JoinColumn (name = "empleadoPaciente")
    private Personal empleadoPaciente ;

    // Varias citas pueden ser gestionadas por la misma persona
    // En cita se guarda la clave foranea de personal
    @ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
    @NotFound(action = NotFoundAction.IGNORE)
    @JoinColumn (name = "empleadoDoctor")
    @Where(clause = "despedido = true")
    private Personal empleadoDoctor;


}
