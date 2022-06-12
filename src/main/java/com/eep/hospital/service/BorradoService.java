package com.eep.hospital.service;

import java.util.List;

public interface BorradoService {
	
	
	String borrarPersonal(List<String> personal);
	
	String borrarPacientes(List<String> pacientes);

	String borrarCitas(List<String> citas);

}
