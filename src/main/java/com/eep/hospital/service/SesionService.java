package com.eep.hospital.service;

import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;
import java.util.List;

public interface SesionService {

    String obtenerCorreoUsuario(boolean inicioSesion);

    Collection<? extends GrantedAuthority> obtenerRolUsuario();

    boolean esRolEspecial(List<String> rolesEspeciales);

    boolean esRolEstandar();

    List<String> obtenerRoles();

}
