package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresCitasComponent;
import com.eep.hospital.component.ConversoresHorarioComponent;
import com.eep.hospital.component.ConversoresPersonalComponent;
import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.dto.CitaGuardadoDto;
import com.eep.hospital.dto.CitaIdDto;
import com.eep.hospital.dto.DatosCitaDto;
import com.eep.hospital.entity.Cita;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.repositorio.CitasJpaRepository;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.HorarioJpaRepository;
import com.eep.hospital.repositorio.PersonalJpaRepository;
import com.eep.hospital.response.PersonalResponseCompleto;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.HashMap;
import java.util.List;

@Service("citasServiceImpl")
public class CitasServiceImpl implements CitasService {

    //El contexto de persistencia se refiere al "traductor" java/Mysql
    @PersistenceContext
    private EntityManager em;



    // =============== Roles
    @Value("${rol.departamento.medicina}")
    private String rol_medicina;

    @Value("${rol.departamento.comunicacion}")
    private String rol_operador;

    @Value("${rol.usuario}")
    private String rol_estandar;

    @Value("${rol.creador}")
    private String rol_creadorWeb;

    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;

    // -------- Servicio para controlar la ubicación del usuario en todo momento
    @Autowired
    @Qualifier("opcionesServiceImpl")
    OpcionesService opcionesService;

    @Autowired
    @Qualifier("citasJpaRepository")
    CitasJpaRepository citasJpaRepository;

    @Autowired
    @Qualifier("personalJpaRepository")
    PersonalJpaRepository personalJpaRepository;

    @Autowired
    @Qualifier("horarioJpaRepository")
    HorarioJpaRepository horarioJpaRepository;

    @Autowired
    @Qualifier("conversoresCitasComponent")
    ConversoresCitasComponent conversoresCitasComponent;

    @Autowired
    @Qualifier("conversoresHorarioComponent")
    ConversoresHorarioComponent conversoresHorarioComponent;

    @Autowired
    @Qualifier("clienteJpaRepository")
    ClienteJpaRepository clienteJpaRepository;

    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;


    // -------- Servicio para obtener el correo del usuario que inició sesión
    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;

    @Autowired
    @Qualifier("filtrosServiceImpl")
    FiltrosService filtrosService;

    @Autowired
    @Qualifier("fechasServiceImpl")
    FechasService fechasService;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    @Override
    public List<PersonalResponseCompleto> buscarCitas(CitaDto datosSeleccionados) {

        List<Personal> horarios;

        try {
            //si no ha filtrado por ningun profesional
            if(datosSeleccionados.getPersonal().getDniNColegiado()=="") {

                //listara todos los doctores y sus horarios * de la especialidad y prestacion seleccionadas

                horarios = personalJpaRepository.horarioTodosLosDoctores(
                                datosSeleccionados.getEspecialidades().getNombreEspecialidad()
                );

            /*
            la fecha disponible se calculara
            si es martes obtener proxima fecha cuyo dia sea martes
             */
            //opcionesService.se


            }else{
                //si ha filtrado por alguno
                //listara el doctor y sus horarios * de la especialidad y prestacion seleccionadas
                horarios = personalJpaRepository.horarioDoctor(
                        datosSeleccionados.getPersonal().getDniNColegiado(),
                        datosSeleccionados.getEspecialidades().getNombreEspecialidad()

                );
            }



            return fechasService.establecerProximaCita(
                    horarios

            );

        }catch (RuntimeException e) {
            HashMap<String, String> error = new HashMap<>();
            error.put("buscarCitas", "Ocurrió un error al buscar las citas.");
            excepcionesService.setMensajeError(error);
            return null;
        }

    }

    @Override
    public Cita guardarCita(DatosCitaDto citaGuardadoDto) {

        Object paraQuien = opcionesService.getParaQuien();

        Personal doctor = personalJpaRepository.findByDniNColegiado(citaGuardadoDto.getDniNColegiado());

        CitaGuardadoDto citaAGuardar = null;

        if (paraQuien instanceof Personal){
            Personal pacienteEmpleado = personalJpaRepository.findByDniNColegiado(citaGuardadoDto.getDniCliente());

            citaAGuardar = new CitaGuardadoDto(
                    citaGuardadoDto.getHora(),
                    LocalDateTime.parse(citaGuardadoDto.getFechaDisponible()).toLocalDate(),
                    null,
                    pacienteEmpleado,
                    doctor
            );
        }else{
            ClienteEntity cliente = clienteJpaRepository.findByDni(citaGuardadoDto.getDniCliente());

            citaAGuardar = new CitaGuardadoDto(
                    citaGuardadoDto.getHora(),
                    LocalDateTime.parse(citaGuardadoDto.getFechaDisponible()).toLocalDate(),
                    cliente,
                    null,
                    doctor
            );
        }


        Cita cita = conversoresCitasComponent.convertirCitaGuardadoAEntity(citaAGuardar);

        return citasJpaRepository.save(cita);
    }


    @Override
    public void borrarCitaDisponible(LocalTime hora, String dniNColegiado) {

        try {
            Query query = em.createNativeQuery(
                    "DELETE FROM citas_disponibles where hora= ? and dniNColegiado = ? ;");

            query.setParameter(1, hora);
            query.setParameter(2, dniNColegiado);
            query.executeUpdate();


            em.close();
        }catch (RuntimeException e){

        }
    }

    @Override
    public void guardarCitaCispoinible(CitaIdDto citaIdDto) {

        try {
            Query query = em.createNativeQuery(
                    "INSERT INTO citas_disponibles (`dnincolegiado`, `hora`) VALUES (?, ?);");


            query.setParameter(2, citaIdDto.getHora() + ":00");
            query.setParameter(1, citaIdDto.getDniNColegiado());

            query.executeUpdate();

            em.close();
        }catch (RuntimeException e){
            System.out.println(e.getMessage());
        }
    }

    @Override
    public boolean cancelarCita(Long id) {

        try {
            Query query = em.createNativeQuery(
                    "DELETE FROM cita where id= ?;");

            query.setParameter(1, id);
            query.executeUpdate();

            em.close();

            mensajesService.setMensaje("Se ha cancelado la cita");

            return true;
        }catch (RuntimeException e){
            HashMap<String, String> error = new HashMap<>();
            error.put("cancelarCita", "Ocurrió un error al cancelar la cita, intentelo de nuevo más tarde");
            excepcionesService.setMensajeError(error);

            em.close();
            return false;
        }


    }
}
