package com.eep.hospital.controller;


import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.response.ClienteResponse;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AltaClienteController {

	// =============== PROPIEDADES ===============

	@Value("${plantilla.altaClientes}")
	private String altaClientes;

	// =============== SERVICIOS ===============
	@Autowired
	@Qualifier("comprobarFormularioImpl")
	CompFormService comprobarFormulario;

	@Autowired
	@Qualifier("clienteServiceImpl")
	ClienteService clienteService;

	@Autowired
	@Qualifier("validarDniServiceImpl")
	ValidarDniService validarDniService;

	@Autowired
	@Qualifier("sesionServiceImpl")
	SesionService sesionService;

	@Autowired
	@Qualifier("comprobacionesServiceImpl")
	ComprobacionesService comprobacionesService;

	@Autowired
	@Qualifier("excepcionesServiceImpl")
	ExcepcionesService excepcionesService;

	// =============== CLASES ===============
	@Autowired
	@Qualifier("clienteDto")
	ClienteDto clienteDto;

	// =============== FLUJO DE LA APLICACIÓN ===============

	@GetMapping("/cliente/alta")
	public ModelAndView mostrarFormularioDeRegistro() {

		ModelAndView mav = new ModelAndView(altaClientes);

		mav.addObject("cliente", clienteDto);
		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
		return mav;

	}

	@PostMapping("/cliente/alta")
	public String registrarCuentaDeUsuario(@Validated @ModelAttribute("cliente") ClienteDto clienteDto, BindingResult result, Model model) {

		model.addAttribute("usuarioWeb", sesionService.obtenerCorreoUsuario(true));

		// Si tiene fallos en el formulario
		if (comprobarFormulario.tieneFallos(result)) {
			model.addAttribute("cliente", clienteDto);
			return altaClientes;
		}

		// Si tiene fallos en el dni
		if (!validarDniService.CalcularLetra(clienteDto.getDni())){

			model.addAttribute("mensajeError", "El DNI no es válido.");
			model.addAttribute("cliente", clienteDto);
			return altaClientes;
		}

		// Comprueba si existe un cliente con el mismo dni
		ClienteResponse clienteResponse = comprobacionesService.existeCliente(clienteDto);

		if (clienteResponse!=null){
			model.addAttribute("mensajeError", "Ya existe un usuario con el mismo DNI.");
			model.addAttribute("cliente", clienteDto);
			return altaClientes;
		}

		// Comprueba si existe un cliente con el mismo correo
		if (comprobacionesService.existeCorreoCliente(clienteDto)){
			String mensajeError = excepcionesService.getMensajeError().get("existeCorreoCliente");
			if (mensajeError!=null){
				model.addAttribute("mensajeError", mensajeError);

			}else{
				model.addAttribute("mensajeError", "Ya existe un usuario con el mismo correo.");
			}

			model.addAttribute("cliente", clienteDto);
			return altaClientes;
		}


		// Finalmente guarda el usuario y retorna al login
		clienteService.guardar(clienteDto);

		return "redirect:/login?exito";
	}



}
