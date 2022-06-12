package com.eep.hospital.service;

import com.eep.hospital.response.PersonalResponseCompleto;

import java.util.List;

public interface OpcionesService {
	Object getParaQuien();

	void setParaQuien(Object paraQuien);

	List<PersonalResponseCompleto> getEmpleados();

	void setEmpleados(List<PersonalResponseCompleto> empleados);

	String getItem_menu();



	void setItem_menu(String item_menu);

	String getFormulario();

	void setFormulario(String formulario);

	String getIdentificador();

	void setIdentificador(String identificador);

	List<String> getIdentificadores();

	void setIdentificadores(List<String> identificadores);

	// Operaciones para el correcto funcionamiento del programa

	
	

}
