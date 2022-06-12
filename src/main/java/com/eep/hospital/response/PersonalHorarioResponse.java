package com.eep.hospital.response;

import com.eep.hospital.entity.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.LinkedHashSet;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalHorarioResponse {

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

    private Departamento departamento;

    PerfilUsuario perfilUsuario;

    EspecialidadEntity especialidad;

    UsuarioEntity usuario;

    private LinkedHashSet<Horario> horarios;
}
