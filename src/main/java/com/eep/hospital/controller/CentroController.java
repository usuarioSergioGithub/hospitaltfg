package com.eep.hospital.controller;

import com.eep.hospital.dto.InventarioDto;
import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/centro")
public class CentroController {

	// =============== Servicios ===============

	@Autowired
	@Qualifier("listadoServiceImpl")
	ListadoService listadoService;

	@Autowired
	@Qualifier("modificacionServiceImpl")
	ModificacionService modificacionService;

	@Value("${plantilla.listadoInventario}")
	private String listadoInventario;

	@Autowired
	@Qualifier("menuServiceImpl")
	MenuService menuService;

	@Autowired
	@Qualifier("sesionServiceImpl")
	SesionService sesionService;

	@Autowired
	@Qualifier("mensajesServiceImpl")
	MensajesService mensajesService;

	@Autowired
	@Qualifier("excepcionesServiceImpl")
	ExcepcionesService excepcionesService;

	// =============== Componentes ===============

	// --------- Componente para el menu ---------

	@Autowired
	private MenuDto menuDto;


	// =============== Propiedades ===============

	@Value("${plantilla.quienesSomos}")
	String quienesSomos;


	// =============== Flujo de l aplicación ===============

	// --- Plantilla quienes somos
	@GetMapping("/quienesomos")
	public ModelAndView quienesSomos() {

		ModelAndView mav = new ModelAndView(quienesSomos);

		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));

		menuDto = menuService.reiniciarElementos();
		menuDto.setCentro("active");
		menuDto.setQuienesSomos("active");


		mav.addObject("menuDto",menuDto);

		return mav;

	}

	// --- Plantilla listado de inventario
	@GetMapping("/listarinventario")
	public ModelAndView listarInventario() {

		ModelAndView mav = new ModelAndView(listadoInventario);
		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
		menuDto = menuService.reiniciarElementos();
		menuDto.setCentro("active");
		menuDto.setConsultarInventario("active");

		mav.addObject("menuDto",menuDto);
		mav.addObject("articulo",new InventarioDto());
		mav.addObject("inventario", listadoService.obtenerInventario());

		return mav;

	}

	// modificación de un articulo
	@PostMapping("/modificarArticulo")
	public ModelAndView modificarInventario(@ModelAttribute InventarioDto inventario) {

		ModelAndView mav = new ModelAndView(listadoInventario);
		mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));

		// Modifica el articulo
		boolean inventarioModificado = modificacionService.modificarInventario(inventario);

		// Muestra mensaje en funcion de si se pudo o no
		if (inventarioModificado){
			mav.addObject("mensaje", mensajesService.getMensaje());
		}else {
			mav.addObject("mensajeError", excepcionesService.getMensajeError().get("modificarInventario"));
		}


		menuDto = menuService.reiniciarElementos();
		menuDto.setCentro("active");
		menuDto.setConsultarInventario("active");

		mav.addObject("menuDto", menuDto);
		mav.addObject("inventario", listadoService.obtenerInventario());

		return mav;

	}


}
