package com.eep.hospital.controller;

import com.eep.hospital.dto.MensajesDto;
import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.dto.TiposAsunto;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class ContactoController {

	@Autowired
	private MenuDto menuDto;

	// =================== SERVICIOS ===================

	@Autowired
	@Qualifier("menuServiceImpl")
	MenuService menuService;

	@Autowired
	@Qualifier("sesionServiceImpl")
	SesionService sesionService;

	@Autowired
	@Qualifier("emailServiceImpl")
	EmailService emailService;

	@Autowired
	@Qualifier("mensajesServiceImpl")
	MensajesService mensajesService;

	@Autowired
	@Qualifier("excepcionesServiceImpl")
	ExcepcionesService excepcionesService;

	// =================== PROPIEDADES ===================

	@Value("${plantilla.formContacto}")
	String formContacto;



	@Autowired
	@Qualifier("mensajesDto")
	private MensajesDto mensajesDto;


	// =================== FLUJO DE LA APLICACIÓN ===================
	
	@GetMapping("/contacta")
	public ModelAndView mostrarForm() {

		ModelAndView mav = new ModelAndView(formContacto);

		menuDto = menuService.reiniciarElementos();
		menuDto.setContacto("active");


		mav.addObject("tipoAsunt", TiposAsunto.values());

		mav.addObject("mensajesDto", mensajesDto);
		mav.addObject("menuDto", menuDto);
		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
		return mav;
	}

	@PostMapping("/contacta")
	public ModelAndView recogerDatos(MensajesDto mensajesDto) {

		ModelAndView mav = new ModelAndView(formContacto);

		menuDto = menuService.reiniciarElementos();
		menuDto.setContacto("active");



		// Esto solo se hará si se guarda en la base de datos
		boolean correoEnviado = emailService.enviarEmail(mensajesDto);

		if(correoEnviado){
			mav.addObject("mensaje",mensajesService.getMensaje());
		}else {
			mav.addObject("mensajeError", excepcionesService.getMensajeError().get("email"));
		}

		mav.addObject("menuDto", menuDto);
		mav.addObject("tipoAsunt", TiposAsunto.values());
		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
		return mav;
	}

}
