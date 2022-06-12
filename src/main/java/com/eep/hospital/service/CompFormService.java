package com.eep.hospital.service;

import org.springframework.validation.BindingResult;

public interface CompFormService {

	// Comprobacion de formularios
	boolean tieneFallos(BindingResult result);

	boolean tieneFallosBajaModif(BindingResult result);

}
