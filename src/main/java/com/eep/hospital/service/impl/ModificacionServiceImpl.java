package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresInvComponent;
import com.eep.hospital.dto.InventarioDto;
import com.eep.hospital.entity.Cita;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.InventarioJpaRepository;
import com.eep.hospital.repositorio.PersonalJpaRepository;
import com.eep.hospital.service.ExcepcionesService;
import com.eep.hospital.service.MensajesService;
import com.eep.hospital.service.ModificacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;

@Service("modificacionServiceImpl")
public class ModificacionServiceImpl implements ModificacionService {


    @Autowired
    @Qualifier("inventarioJpaRepository")
    InventarioJpaRepository inventarioJpaRepository;

    @Autowired
    @Qualifier("conversoresInvComponent")
    ConversoresInvComponent conversoresInvComponent;

    @Autowired
    @Qualifier("personalJpaRepository")
    PersonalJpaRepository personalJpaRepository;

    @Autowired
    @Qualifier("clienteJpaRepository")
    ClienteJpaRepository clienteJpaRepository;

    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    @Override
    public boolean modificarInventario(InventarioDto inventario) {

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        try {

            inventario.setPersonal(Arrays.asList(
                    personalJpaRepository.findByCorreo(auth.getName()))
            );

            inventarioJpaRepository.save(
                    conversoresInvComponent.convertirInvAEntity(inventario)
            );

            mensajesService.setMensaje("Artículo modelo " + inventario.getModelo() + " modificado.");
            return true;

        }catch (RuntimeException e){
            HashMap<String, String> error = new HashMap<>();
            error.put("modificarInventario", "Ocurrió un error al modificar el artículo modelo " +
                    inventario.getModelo() + ".");
            excepcionesService.setMensajeError(error);
            return false;
        }

    }

    @Override
    public boolean modificarEmpleado(Personal empleado) {

        try {

            HashSet<Cita> objects = new HashSet<>();

            objects.add(new Cita());

            empleado.setCitas(objects);

            personalJpaRepository.save(empleado);
            mensajesService.setMensaje("Empleado con DNI " + empleado.getDniNColegiado() + " modificado.");
            return true;

        }catch (RuntimeException e){
            HashMap<String, String> error = new HashMap<>();
            error.put("modificarEmpleado", "Ocurrió un error al modificar el empleado con DNI " +
                    empleado.getDniNColegiado() + ". Error: " + e.getMessage());
            excepcionesService.setMensajeError(error);
            return false;
        }

    }

    @Override
    public boolean modificarCliente(ClienteEntity cliente) {
        try {

            HashSet<Cita> objects = new HashSet<>();

            objects.add(new Cita());

            cliente.setCitas(objects);

            clienteJpaRepository.save(cliente);
            mensajesService.setMensaje("Cliente con DNI " + cliente.getDni() + " modificado.");
            return true;

        }catch (RuntimeException e){
            HashMap<String, String> error = new HashMap<>();
            error.put("modificarCliente", "Ocurrió un error al modificar el cliente con DNI " +
                    cliente.getDni() + ". Error: " + e.getMessage());
            excepcionesService.setMensajeError(error);
            return false;
        }
    }
}
