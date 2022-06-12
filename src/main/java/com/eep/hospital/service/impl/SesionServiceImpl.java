
package com.eep.hospital.service.impl;

import com.eep.hospital.response.ClienteResponse;
import com.eep.hospital.service.ListadoService;
import com.eep.hospital.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Service("sesionServiceImpl")
public class SesionServiceImpl implements SesionService {

    @Value("${rol.usuario}")
    private String rolEstandarEstablecido;


    @Override
    public String obtenerCorreoUsuario(boolean inicioSesion) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        String correo = auth.getName();

        if (inicioSesion){

            //List<ClienteResponse> clientes = listadoService.listarPacientes(null, correo);

            // como el correo es unico solo obtendra 1 elemento
            //return clientes.get(0).getNombre();

            if (!correo.equals("anonymousUser")){
                correo = correo.split("@")[0];
                correo = correo.toUpperCase().charAt(0) + correo.substring(1, correo.length()).toLowerCase();
            }
        }


        return correo;

    }

    @Override
    public Collection<? extends GrantedAuthority> obtenerRolUsuario() {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        return auth.getAuthorities();
    }

    @Override
    public boolean esRolEspecial(List<String> rolesEspeciales) {

        Collection<? extends GrantedAuthority> roles = this.obtenerRolUsuario();

        boolean rolEspecial = false;

        for (GrantedAuthority rol: roles) {

            for (String rolEncontrado: rolesEspeciales) {
                if (rolEncontrado.equals(rol.toString())){
                    rolEspecial = true;
                }
            }

        }

        return rolEspecial;
    }

    @Override
    public boolean esRolEstandar() {
        Collection<? extends GrantedAuthority> roles = this.obtenerRolUsuario();

        boolean rolEstandar = false;

        for (GrantedAuthority rol: roles) {


            if (rol.toString().equals(rolEstandarEstablecido)){
                rolEstandar = true;
            }


        }

        return rolEstandar;


    }

    @Override
    public List<String> obtenerRoles() {
        Collection<? extends GrantedAuthority> grantedAuthorities = obtenerRolUsuario();
        List<? extends GrantedAuthority> roles = grantedAuthorities.stream().collect(Collectors.toList());

        return roles.stream().map(rol -> rol.getAuthority()).collect(Collectors.toList());

    }
}
