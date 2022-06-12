package com.eep.hospital.service.impl;

import com.eep.hospital.response.PersonalResponseCompleto;
import com.eep.hospital.service.OpcionesService;
import org.springframework.stereotype.Service;

import java.util.List;


@Service("opcionesServiceImpl")
public class OpcionesServiceImpl implements OpcionesService {


	private List<PersonalResponseCompleto> empleados;
	private Object paraQuien;

	@Override
	public Object getParaQuien() {
		return paraQuien;
	}

	@Override
	public void setParaQuien(Object paraQuien) {
		this.paraQuien = paraQuien;
	}

	@Override
	public List<PersonalResponseCompleto> getEmpleados() {
		return empleados;
	}
	@Override
	public void setEmpleados(List<PersonalResponseCompleto> empleados) {
		this.empleados = empleados;
	}

	private String item_menu; // para mostar al usuario la ubicacion dentro de la pagina
	private String formulario;// Controlar de que formulario viene el usuario
	private String identificador;// Guardar un pasaporte para despues realizar distintas operaciones
	private List<String> identificadores;// Guardar todos los pasaportes de los alumnos que posteriormente se borrarán


	@Override
	public String getItem_menu() {

		return item_menu;
	}


	@Override
	public void setItem_menu(String item_menu) {
		this.item_menu = item_menu;
	}


	@Override
	public String getFormulario() {
		return formulario;
	}


	@Override
	public void setFormulario(String formulario) {
		this.formulario = formulario;
	}


	@Override
	public String getIdentificador() {
		return identificador;
	}


	@Override
	public void setIdentificador(String identificador) {
		this.identificador = identificador;
	}


	@Override
	public List<String> getIdentificadores() {
		return identificadores;
	}


	@Override
	public void setIdentificadores(List<String> identificadores) {
		this.identificadores = identificadores;
	}
}
