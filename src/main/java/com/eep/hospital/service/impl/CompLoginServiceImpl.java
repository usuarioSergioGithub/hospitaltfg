package com.eep.hospital.service.impl;

import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.service.CompLoginService;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

@Service ("compLoginServiceImpl")
public class CompLoginServiceImpl implements CompLoginService {

	@Override
	public String ComprobarLogin(String url, HttpSession sesion) {

		ClienteEntity cliente = (ClienteEntity) sesion.getAttribute("datos_sesion");

		if (cliente.getDni() == null) {

			Personal personal = (Personal) sesion.getAttribute("datos_sesion");

			if (personal.getDniNColegiado() == null) {
				return "index";
			}

			return url;
		}

		return url;
	}
}
