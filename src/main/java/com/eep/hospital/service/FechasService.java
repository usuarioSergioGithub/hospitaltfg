package com.eep.hospital.service;

import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.PersonalResponseCompleto;

import java.time.LocalDateTime;
import java.util.List;

public interface FechasService {

    List<PersonalResponseCompleto> establecerProximaCita(List<Personal> empleados);
    LocalDateTime obtenerProximaFecha(int diaDisponible);
}
