package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresPersonalComponent;
import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.PersonalJpaRepository;
import com.eep.hospital.response.PersonalHorarioResponse;
import com.eep.hospital.response.PersonalResponse;
import com.eep.hospital.response.PersonalResponseCompleto;
import com.eep.hospital.service.FiltrosService;
import com.eep.hospital.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("filtrosServiceImpl")
public class FiltrosServiceImpl implements FiltrosService {

    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;

    @Autowired
    @Qualifier("personalJpaRepository")
    PersonalJpaRepository personalJpaRepository;

    @Autowired
    @Qualifier("clienteJpaRepository")
    ClienteJpaRepository clienteJpaRepository;
    
    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;


    @Override
    public List<PersonalHorarioResponse> quitarEmpleado(List<PersonalResponseCompleto> empleados, Object usuario) {


        PersonalResponseCompleto empleadoAQuitar = null;

        String correo = comprobarObjetoDevolverCorreo(usuario);

        // (No se puede quitar directamente en el bucle porque sino lanza una excepcion)
        for (PersonalResponseCompleto empleado: empleados) {

            if (empleado.getCorreo().equals(correo)){

                empleadoAQuitar = empleado;
            }

        }

        empleados.remove(empleadoAQuitar);

        return conversoresPersonalComponent.convPersonalComplARespuestas(empleados);
    }

    @Override
    public List<PersonalResponse> quitarEmpleadoResponse(List<Personal> empleados, String correo) {
        Personal empleadoAQuitar = null;

        // (No se puede quitar directamente en el bucle porque sino lanza una excepcion)
        for (Personal empleado: empleados) {

            if (empleado.getCorreo().equals(correo)){

                empleadoAQuitar = empleado;
            }

        }

        empleados.remove(empleadoAQuitar);

        return conversoresPersonalComponent.convertirPersonalARespuestas(empleados);
    }

    @Override
    public Object paraQuien(CitaDto citaDto) {

        boolean rolEstandar = sesionService.esRolEstandar();

        // si no es usuario estandar
        if(!rolEstandar){

            //si la cita es para mi significa que el correo que llega hay que buscarlo en personal
            if (citaDto.isParaMi()){

                String correo = sesionService.obtenerCorreoUsuario(false);

                Personal byCorreo = personalJpaRepository.findByCorreo(correo);
                // --- PERSONAL
                return byCorreo;



            // si la cita que llega no es para mi habra que buscar el correo que se ha introducido en cliente
            }else{
                // --- PACIENTES
                ClienteEntity cliente = clienteJpaRepository.findByCorreo(citaDto.getCorreoIntroducido());

                if (cliente==null){
                    return personalJpaRepository.findByCorreo(citaDto.getCorreoIntroducido());
                }

                return cliente;



            }

        //si es usuario estandar obtiene de sesion
        }else{
            String s = sesionService.obtenerCorreoUsuario(false);
            // --- PACIENTES
            return clienteJpaRepository.findByCorreo(s);

        }

    }

    @Override
    public String comprobarObjetoDevolverDni(Object usuario){

        if (usuario instanceof Personal){
            Personal personal = (Personal) usuario;
            return  personal.getDniNColegiado();
        }

        if (usuario instanceof ClienteEntity){
            ClienteEntity personal = (ClienteEntity) usuario;
            return  personal.getDni();
        }

        return null;
    }

    @Override
    public String comprobarObjetoDevolverCorreo(Object usuario) {

        if (usuario instanceof Personal){
            Personal personal = (Personal) usuario;
            return  personal.getCorreo();
        }

        if (usuario instanceof ClienteEntity){
            ClienteEntity personal = (ClienteEntity) usuario;
            return  personal.getCorreo();
        }

        return null;
    }

}
