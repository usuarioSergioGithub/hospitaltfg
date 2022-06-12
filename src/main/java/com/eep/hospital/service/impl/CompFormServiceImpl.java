package com.eep.hospital.service.impl;

import com.eep.hospital.service.CompFormService;
import org.springframework.stereotype.Service;
import org.springframework.validation.BindingResult;


@Service("comprobarFormularioImpl")
public class CompFormServiceImpl implements CompFormService {

	// Para comprobar *todos* los datos del cliente
	@Override
	public boolean tieneFallos(BindingResult result) {
		return result.hasErrors()? true : false;

	}
	
	// Para comprobar *solo* el dni del cliente
	@Override
	public boolean tieneFallosBajaModif(BindingResult result) {

		return result.hasFieldErrors("matricula")? true : false;

	}

}
