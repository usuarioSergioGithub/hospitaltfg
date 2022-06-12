package com.eep.hospital.service;


import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.response.ClienteResponse;
import com.eep.hospital.response.IntervencionesResponse;
import org.springframework.security.core.Authentication;

import java.util.List;

public interface ComprobacionesService {
	
	// Comprobar si existe un cliente en la base de datos
	ClienteResponse existeCliente(ClienteDto clienteDto);

	boolean existeCorreoCliente(ClienteDto clienteDto);

	List<IntervencionesResponse> usuarioAutenticado(Authentication authentication);

	boolean quedanEmpleadosPorRol(List<String> empleadosDni);
}

