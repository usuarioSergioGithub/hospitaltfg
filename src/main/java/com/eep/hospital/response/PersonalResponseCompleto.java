package com.eep.hospital.response;

import com.eep.hospital.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalResponseCompleto {

    private String dniNColegiado;

    private String nombre;

    private String correo;

    private String apellidos;

    private String direccion;

    private String localidad;

    private String provincia;

    private int cp;

    private String telFijo;

    private String movil;

    private String diaDisponible;

    private LocalDateTime proximaCita;

    private LocalTime disponibleDesde;

    private LocalTime disponibleHasta;

    private List<Cita> empleadoPaciente ;

    private List<Cita> empleadoDoctor;

    private Departamento departamento;

    PerfilUsuario perfilUsuario;

    EspecialidadEntity especialidad;

    UsuarioEntity usuario;

    private Set<Horario> horarios;
}
