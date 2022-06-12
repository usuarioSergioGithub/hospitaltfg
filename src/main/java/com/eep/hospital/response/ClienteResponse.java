package com.eep.hospital.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteResponse {

    private String dni;

    private String nombre;

    private String apellidos;

    private String correo;

    private String direccion;

    private String localidad;

    private String provincia;

    private String cp;

    private String telFijo;

    private String movil;

    private boolean pagada;
}
