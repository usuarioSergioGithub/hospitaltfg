package com.eep.hospital.response;

import com.eep.hospital.entity.PerfilUsuario;
import com.eep.hospital.entity.UsuarioEntity;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ClienteResponseCompleto {

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

    private PerfilUsuario perfilUsuario;

    private UsuarioEntity usuario;
}
