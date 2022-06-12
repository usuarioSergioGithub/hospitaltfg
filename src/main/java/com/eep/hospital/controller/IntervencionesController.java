package com.eep.hospital.controller;

import com.eep.hospital.component.ConversoresEspecialidadComponent;
import com.eep.hospital.component.ConversoresPersonalComponent;
import com.eep.hospital.component.ConversoresPrestacionComponent;
import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.response.IntervencionesResponse;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@RestController
public class IntervencionesController {

    // ***************** Servicios *********************

    // -------- Servicio para los mensajes
    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    // -------- Servicio para el listado
    @Autowired
    @Qualifier("listadoServiceImpl")
    ListadoService listadoService;

    // ***************** Componentes *********************

    // --------- Componente para el menu ---------

    @Autowired
    private MenuDto menuDto;

    @Autowired
    @Qualifier("menuServiceImpl")
    MenuService menuService;


    // ***************** application.properties ********************

    // -------- Plantillas HTML -------------
    @Value("${plantilla.listadoIntervenciones}")
    private String listadoIntervenciones;


    // ------- Posibles ubicaciones del usuario ----------
    @Value("${consulta.personal}")
    private String consultaPersonal;

    @Value("${consulta.citas}")
    private String consultaCitas;

    @Value("${consulta.pacientes}")
    private String consultaPacientes;

    @Autowired
    @Qualifier("citasServiceImpl")
    CitasService citasService;

    @Autowired
    @Qualifier("citaDto")
    CitaDto citaDto;

    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;

    @Autowired
    @Qualifier("conversoresEspecialidadComponent")
    ConversoresEspecialidadComponent conversoresEspecialidadComponent;

    @Autowired
    @Qualifier("conversoresPrestacionComponent")
    ConversoresPrestacionComponent conversoresPrestacionComponent;


    @Autowired
    @Qualifier("comprobacionesServiceImpl")
    ComprobacionesService comprobacionesService;

    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;
    /*
    realmente como el que ha iniciado sesion tiene un rol en concreto,
    no le dejara acceder a la url de listar de pacente si es doctor etc
    por tanto solo tendria que recoger el usuario que ha iniciado sesion
     */

    @GetMapping("/listar/intervenciones")
    public ModelAndView mostrarIntervenciones() {

        ModelAndView mav = new ModelAndView(listadoIntervenciones);

        Authentication auth = SecurityContextHolder.getContext().getAuthentication();

        List<IntervencionesResponse> intervencionesResponses = comprobacionesService.usuarioAutenticado(auth);

        // Listará una cosa u otra en función del usuario autenticado

        menuDto = menuService.reiniciarElementos();
        menuDto.setIntervenciones("active");
        menuDto.setConsultarIntervenciones("active");


        mav.addObject("centro", listadoService.listarCentro());
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        mav.addObject("menuDto", menuDto);
        mav.addObject("intervenciones", intervencionesResponses);
        mav.addObject("titulo", mensajesService.getMensaje());

        return mav;

    }

    @GetMapping("/realizarpago")
    public ModelAndView realizarPago(){

        //mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario());
        //menuDto = menuService.reiniciarElementos();
        return null;
    }


}
