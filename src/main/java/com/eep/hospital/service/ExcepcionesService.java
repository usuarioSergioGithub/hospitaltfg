package com.eep.hospital.service;

import java.util.HashMap;

public interface ExcepcionesService {

	// Controlar excepciones
	HashMap<String, String> getMensajeError();

	void setMensajeError(HashMap<String, String> mensajeError);



}
