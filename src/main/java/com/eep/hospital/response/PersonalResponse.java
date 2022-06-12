package com.eep.hospital.response;

import com.eep.hospital.entity.PerfilUsuario;
import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PersonalResponse {

    private String dniNColegiado;
    private boolean esOperador;
    private String nombre;
    private String correo;
    private String apellidos;
    private String direccion;
    private String localidad;
    private String provincia;
    private String movil;
    private PerfilUsuario perfilUsuario;
}
