package com.eep.hospital.service;


import com.eep.hospital.entity.Centro;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.*;

import java.util.List;

public interface ListadoService {

    Centro listarCentro();

    List<IntervencionesResponse> listarIntervenciones(String paciente, String doctor);

    List<PersonalResponse> listarPersonal(String rol, String especialidad);

    // la diferencia de este y el de arriba esta en lo que es el response
    /*
    Response se utiliza cuando solo queremos unos datos en concreto
    el de arriba lo utilizaría para ese caso
    el de abajo para uando quiera todos los datos
     */
    List<PersonalResponseCompleto> listarPersonalCompleto();

    List<ClienteResponseCompleto> listarClienteCompleto();

    // filtroSeleccionado -> para cuando por ejemplo es operador y el mismo tiene cita, en lugar de buscar todas, filtra por las suyas
    List<CitaResponse> listarCitasPorRol(boolean filtroSeleccionado);

    List<ClienteResponse> listarPacientes(String dni, String correo);

    Personal obtenerEmpleadoPorDni(String dni);

    List<FacturasResponse> listarFacturas(boolean pagada);

    List<FacturasResponse> listarTodasLasFacturas();

    List<CitaResponse> listarCitas(String correoPaciente, String fecha);

    List<EspecialidadResponse> obtenerTodasLasEspecialidades(String doctor);

    List<PrestacionesResponse> obtenerTodasLasPrestaciones(String especialidad);

    List<String> obtenerPacientesABorrar(List<String> pacientesDNI);

    List<String> obtenerPersonalABorrar(List<String> personalDNINcolegiado);

    List<String> obtenerCitasABorrar(List<String> citasFecha);

    List<InventarioResponse> obtenerInventario();
}
