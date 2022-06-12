package com.eep.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("menuDto")
public class MenuDto {

    private String paginaPrincipal;
    private String intervenciones;
    private String consultarIntervenciones;
    private String realizarPago;
    private String centro;
    private String quienesSomos;
    private String consultarInventario;
    private String citas;
    private String pedirCita;
    private String consultarCitas;
    private String consulta;
    private String consultarPacientes;
    private String pacientesPendientes;
    private String consultarPersonal;
    private String contacto;

    /*
    En cada elemento del menu pongo menuDto.<nombre del atributo>
    desde cada mapping, pasare el objeto pero dependiendo donde se encuentre hare un set y pondre "active"
     */

}
