package com.eep.hospital.service.impl;

import com.eep.hospital.service.ExcepcionesService;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service("excepcionesServiceImpl")
public class ExcepcionesServiceImpl implements ExcepcionesService {
	
	private HashMap<String, String> mensajeError = new HashMap<String, String>();

	// Devolver la excepcion que se generó
	@Override
	public HashMap<String, String> getMensajeError() {
		return mensajeError;
	}

	// Guardar la excepcion
	@Override
	public void setMensajeError(HashMap<String, String> mensajeError) {
		this.mensajeError = mensajeError;
	}
	
	
	
}
