package com.eep.hospital.service.impl;

import com.eep.hospital.repositorio.CitasJpaRepository;
import com.eep.hospital.repositorio.ClienteIntervDoctorJpaRepository;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.PersonalJpaRepository;
import com.eep.hospital.service.BorradoService;
import com.eep.hospital.service.ExcepcionesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

@Service("borradoServiceImpl")
public class BorradoServiceImpl implements BorradoService {


	// ====================== REPOSITORIOS ======================
	@Autowired
	@Qualifier("personalJpaRepository")
	PersonalJpaRepository personalJpaRepository;

	@Autowired
	@Qualifier("clienteIntervDoctorJpaRepository")
	ClienteIntervDoctorJpaRepository clienteIntervDoctorJpaRepository;

	@Autowired
	@Qualifier("citasJpaRepository")
	CitasJpaRepository citasJpaRepository;

	@Autowired
	@Qualifier("clienteJpaRepository")
	ClienteJpaRepository clienteJpaRepository;

	// ====================== SERVICIO ======================
	@Autowired
	@Qualifier("excepcionesServiceImpl")
	ExcepcionesService excepcionesService;


	// Borrar varios empleados en una sola operación
	@Override
	public String borrarPersonal(List<String> dniNColegiadoPersonal) {
		
		String mensaje="";

		try {

			personalJpaRepository.deleteByDniNColegiadoIn(dniNColegiadoPersonal);

			mensaje = dniNColegiadoPersonal.size()==1?  "Se ha eliminado el empleado con DNI " + dniNColegiadoPersonal.get(0) + "." : "Se han eliminado " +
					dniNColegiadoPersonal.size() + " empleados.";
			
		}catch (RuntimeException e) {
			HashMap<String, String> error = new HashMap<>();
			error.put("borradoPersonal", "Ocurrió un error al eliminar los empleados. Error: " + e.getMessage());
			excepcionesService.setMensajeError(error);
			return null;
		}
		
		return mensaje;
	}

	// Borrar varios pacientes en una sola operación
	@Override
	public String borrarPacientes(List<String> pacientesDNI) {
		String mensaje="";

		try {
			clienteJpaRepository.deleteByDniIn(pacientesDNI);

			mensaje = pacientesDNI.size()==1?  "Se ha eliminado el paciente con DNI " + pacientesDNI.get(0) + "." : "Se han eliminado " +
					pacientesDNI.size() + " pacientes.";

		}catch (RuntimeException e) {
			HashMap<String, String> error = new HashMap<String, String>();
			error.put("borradoPacientes", "Ocurrió un error al eliminar los pacientes.");
			excepcionesService.setMensajeError(error);
			return null;
		}

		return mensaje;
	}

	// Borrar varias citas en una sola operación
	@Override
	public String borrarCitas(List<String> citasFecha) {
		String mensaje="";

		try {
			citasJpaRepository.deleteByHoraIn(citasFecha);

			mensaje = citasFecha.size()==1?  "Se ha eliminado la cita para la fecha " + citasFecha.get(0) + "." : "Se han eliminado " +
					citasFecha.size() + " citas.";

		}catch (RuntimeException e) {
			HashMap<String, String> error = new HashMap<String, String>();
			error.put("borradoCitas", "Ocurrió un error al eliminar las citas.");
			excepcionesService.setMensajeError(error);
			return null;
		}

		return mensaje;
	}


}
