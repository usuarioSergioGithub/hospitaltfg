package com.eep.hospital.service;

import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.dto.CitaIdDto;
import com.eep.hospital.dto.DatosCitaDto;
import com.eep.hospital.entity.Cita;
import com.eep.hospital.response.PersonalResponseCompleto;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalTime;
import java.util.List;

public interface CitasService {


    List<PersonalResponseCompleto> buscarCitas(CitaDto datosSeleccionados);

    Cita guardarCita(DatosCitaDto citaGuardadoDto);

    @Transactional // Establecer que mientras se ejecuta esta consulta no se podrá realizar ninguna otra
    void borrarCitaDisponible(LocalTime hora, String dniNColegiado);

    @Transactional
    void guardarCitaCispoinible(CitaIdDto citaIdDto);

    @Transactional // Establecer que mientras se ejecuta esta consulta no se podrá realizar ninguna otra
    boolean cancelarCita(Long id);
}
