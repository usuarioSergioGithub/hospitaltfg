package com.eep.hospital.service.impl;

import com.eep.hospital.service.MensajesService;
import org.springframework.stereotype.Service;

@Service("mensajesServiceImpl")
public class MensajesServiceImpl implements MensajesService {
	
	private String mensaje;

	// Mostra mensajes al usuario
	
	@Override
	public String getMensaje() {
		// Retornar el mensaje pero borrarlo para que se muestre solo 1 vez
		String auxMensaje=mensaje;
		this.mensaje=null;
		return auxMensaje;
	}

	@Override
	public void setMensaje(String mensaje) {
		this.mensaje = mensaje;
	}
	
	
}
