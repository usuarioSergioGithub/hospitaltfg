package com.eep.hospital.controller;

import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.service.SesionService;
import com.eep.hospital.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class InicioSesionControlador {

	// =================== SERVICIOS ===================

	@Autowired
	private UsuarioServicio servicio;

	@Autowired
	@Qualifier("sesionServiceImpl")
	SesionService sesionService;

	@Autowired
	private MenuDto menuDto;

	// =================== FLUJO ===================

	// --- pagina de inicio de sesion
	@GetMapping("/login")
	public String iniciarSesion() {
		return "login";
	}

	// --- pagina principal
	@GetMapping("/")
	public String verPaginaDeInicio(Model model) {

		menuDto = new MenuDto();

		menuDto.setPaginaPrincipal("active");
		model.addAttribute("menuDto", menuDto);
		model.addAttribute("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
		return "index";
	}

}
