package com.eep.hospital.service;

import javax.servlet.http.HttpSession;

public interface CompLoginService {

	public abstract String ComprobarLogin(String index, HttpSession sesion);

}
