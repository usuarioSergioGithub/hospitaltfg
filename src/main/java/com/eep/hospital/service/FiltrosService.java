package com.eep.hospital.service;

import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.PersonalHorarioResponse;
import com.eep.hospital.response.PersonalResponse;
import com.eep.hospital.response.PersonalResponseCompleto;

import java.util.List;

public interface FiltrosService {

    List<PersonalHorarioResponse> quitarEmpleado(List<PersonalResponseCompleto> empleados, Object usuario);

    List<PersonalResponse> quitarEmpleadoResponse(List<Personal> empleados, String correo);

    Object paraQuien(CitaDto datosSeleccionados);

    String comprobarObjetoDevolverDni(Object usuario);

    String comprobarObjetoDevolverCorreo(Object usuario);
}
