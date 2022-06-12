package com.eep.hospital.controller;

import com.eep.hospital.component.ConversoresEspecialidadComponent;
import com.eep.hospital.component.ConversoresPersonalComponent;
import com.eep.hospital.component.ConversoresPrestacionComponent;
import com.eep.hospital.dto.CitaDto;
import com.eep.hospital.dto.CitaIdDto;
import com.eep.hospital.dto.DatosCitaDto;
import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.response.*;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@RestController
public class CitasController {

    // =============== Servicios ===============

    // -------- Servicio para el listado
    @Autowired
    @Qualifier("listadoServiceImpl")
    ListadoService listadoService;

    // -------- Servicio para controlar la ubicación del usuario en todo momento
    @Autowired
    @Qualifier("opcionesServiceImpl")
    OpcionesService opcionesService;

    // -------- Servicio para obtener el correo del usuario que inició sesión
    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;

    // -------- Servicio para controlar las excepciones
    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;

    // -------- Servicio para comprobar el formulario
    @Autowired
    @Qualifier("comprobarFormularioImpl")
    CompFormService comprobarFormulario;

    @Autowired
    @Qualifier("filtrosServiceImpl")
    FiltrosService filtrosService;

    // =============== Componentes ===============

    // --------- Componente para el menu ---------

    @Autowired
    private MenuDto menuDto;

    @Autowired
    @Qualifier("menuServiceImpl")
    MenuService menuService;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    // =============== application.properties ===============

    // -------- Plantillas HTML -------------
    @Value("${plantilla.altaCitas}")
    private String altaCitas;

    @Value("${plantilla.listadoPersonal}")
    private String listadoPersonal;

    @Value("${plantilla.listadoCitas}")
    private String listadoCitas;

    @Value("${plantilla.listadoPacientes}")
    private String listadoPacientes;

    @Value("${plantilla.pedirCita}")
    private String pedirCita;

    // -------- Roles
    @Value("${rol.departamento.medicina}")
    private String rol_medicina;

    @Value("${rol.departamento.comunicacion}")
    private String rol_operador;

    @Value("${rol.usuario}")
    private String rol_estandar;

    @Value("${rol.creador}")
    private String rol_creadorWeb;



    // =============== Posibles ubicaciones del usuario ===============
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
    @Qualifier("citaIdDto")
    CitaIdDto citaIdDto;

    // =============== Conversores ===============

    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;

    @Autowired
    @Qualifier("conversoresEspecialidadComponent")
    ConversoresEspecialidadComponent conversoresEspecialidadComponent;

    @Autowired
    @Qualifier("conversoresPrestacionComponent")
    ConversoresPrestacionComponent conversoresPrestacionComponent;

    // =============== FLUJO DE LA APICACION ===============

    // Mostrar formulario de las citas
    @GetMapping("/area/pedircita")
    public ModelAndView nuevaCita(){

        ModelAndView mav = new ModelAndView(pedirCita);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));

        // Lista cada opcion de los select (doctor, especialidad y prestacion)
        List<PersonalResponse> doctores = listadoService.listarPersonal("doctor", null);
        List<PrestacionesResponse> prestaciones = listadoService.obtenerTodasLasPrestaciones(null);
        List<EspecialidadResponse> especialidadesTotales = listadoService.obtenerTodasLasEspecialidades(null);

        menuDto = menuService.reiniciarElementos();
        menuDto.setCitas("active");
        menuDto.setPedirCita("active");

        // Envía todos los datos al formulario
        mav.addObject("menuDto",menuDto);
        mav.addObject("habilitar", false);
        mav.addObject("selected", "selected");
        mav.addObject("citaGuardado", new DatosCitaDto());
        mav.addObject("citaDto", citaDto);
        mav.addObject("especialidadesLista", especialidadesTotales);
        mav.addObject("personalLista", doctores);
        mav.addObject("prestacionesLista", prestaciones);

        return mav;
    }

    // Cada vez que cambie la especialidad recarga los datos
    @GetMapping("/recargardatos")
    public ModelAndView recargardatos(
            @RequestParam(name = "especialidad", required = false) String especialidad,
            @RequestParam(name = "doctor", required = false) String doctor,
            @RequestParam(name = "prestacion", required = false) String prestacion,
            @RequestParam(name = "paraMi", required = false) boolean paraMi){

        ModelAndView mav = new ModelAndView(pedirCita);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        citaDto = new CitaDto();


        // Vuelve a listar cada opcion de los select (doctor, especialidad y prestacion) en funcion de la especialidad
        List<PersonalResponse> doctoresDeEspecialidad = listadoService.listarPersonal(null, especialidad);
        List<EspecialidadResponse> especialidadesTotales = listadoService.obtenerTodasLasEspecialidades(null);
        List<PrestacionesResponse> prestacionesDeEspecialidad = listadoService.obtenerTodasLasPrestaciones(especialidad);


        citaDto.setParaMi(paraMi);


        menuDto = menuService.reiniciarElementos();
        menuDto.setCitas("active");
        menuDto.setPedirCita("active");

        mav.addObject("menuDto",menuDto);
        mav.addObject("habilitar", true);
        mav.addObject("selected", "");
        mav.addObject("especialidadSelec", especialidad);
        mav.addObject("doctorSelec", doctor);
        mav.addObject("prestacionSelec", prestacion);
        mav.addObject("citaGuardado", new DatosCitaDto());
        mav.addObject("citaDto", citaDto);
        mav.addObject("especialidadesLista", especialidadesTotales);
        mav.addObject("personalLista", doctoresDeEspecialidad);
        mav.addObject("prestacionesLista", prestacionesDeEspecialidad);


        return mav;
    }


    // Muestra las citas disponibles en base a lo que selecciono
    @PostMapping("/mostrarCitas")
    public ModelAndView mostrarHorario(@Validated @ModelAttribute("citaDto") CitaDto citaDto, BindingResult result){

        ModelAndView mav = new ModelAndView(pedirCita);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));


        // Vuelve a enviar los datos de los select para que pueda marcrlos
        List<PrestacionesResponse> prestacionesDeEspecialidad = listadoService
                .obtenerTodasLasPrestaciones(citaDto.getEspecialidades().getNombreEspecialidad());

        List<PersonalResponse> doctoresDeEspecialidad = listadoService
                .listarPersonal(null, citaDto.getEspecialidades().getNombreEspecialidad());

        List<EspecialidadResponse> especialidadesTotales = listadoService.obtenerTodasLasEspecialidades(null);


        menuDto = menuService.reiniciarElementos();
        menuDto.setCitas("active");
        menuDto.setConsultarCitas("active");

        mav.addObject("menuDto",menuDto);
        mav.addObject("habilitar", true);
        mav.addObject("selected", "");
        mav.addObject("especialidadSelec", citaDto.getEspecialidades().getNombreEspecialidad());
        mav.addObject("doctorSelec", citaDto.getPersonal().getDniNColegiado());
        mav.addObject("prestacionSelec", citaDto.getPrestaciones().getNombrePrestacion());

        mav.addObject("citaDto", citaDto);
        mav.addObject("citaGuardado", new DatosCitaDto());
        mav.addObject("especialidadesLista", especialidadesTotales);
        mav.addObject("personalLista", doctoresDeEspecialidad);
        mav.addObject("prestacionesLista", prestacionesDeEspecialidad);

        boolean rolEstandar = sesionService.esRolEstandar();


        // Cuando la cita no es para si mismo, es rol especial y la va a pedir para otra persona
        // si el correo que mete esta en blanco le salta el mensaje
        if (comprobarFormulario.tieneFallos(result) && !citaDto.isParaMi() && !rolEstandar) {

            return  mav;
        }

        // Busca las citas en funcion de lo seleccionado
        List<PersonalResponseCompleto> citasDisponibles = citasService.buscarCitas(citaDto);

        // Obtiene los datos del usuario que quiere la cita
        Object usuario = filtrosService.paraQuien(citaDto);

        // Si no existe el correo
        if(usuario==null){
            mav.addObject("mensajeError", "El correo introducido no existe");
        }

        // etsablecer para quien es la cita
        opcionesService.setParaQuien(usuario);

        // Filtra por todos los empleados menor el que inició sesión
        List<PersonalHorarioResponse> citasDisponiblesFiltrado = filtrosService.quitarEmpleado(
                citasDisponibles,
                usuario
        );
        String mensajeError = excepcionesService.getMensajeError().get("buscarCitas");

        // Si ocurre una excepción
        if (citasDisponibles ==null && mensajeError!=null) {

            mav.addObject("mensajeError", mensajeError);

        }



        mav.addObject("dniUsuario", filtrosService.comprobarObjetoDevolverDni(filtrosService.paraQuien(citaDto)));

        if (usuario!=null){
            mav.addObject("citasDisponibles", citasDisponiblesFiltrado);
        }


        return mav;
    }

    @PostMapping("/guardarCita")
    public void guardarCita(@ModelAttribute DatosCitaDto citaGuardadoDto, HttpServletResponse response) throws IOException {


        // aqui se guarda la cita
        citasService.guardarCita(citaGuardadoDto);

        // aqui se borra esa hora
        citasService.borrarCitaDisponible(citaGuardadoDto.getHora(), citaGuardadoDto.getDniNColegiado());

        response.sendRedirect("/area/citas/totales?mensaje=Se ha guardado su cita.");


    }

    // listado de todas las citas
    @GetMapping("/area/citas/totales")
    public ModelAndView misCitas(
            @RequestParam(name = "filtroSeleccionado", required = false) boolean filtroSeleccionado,
            @RequestParam(name = "mensaje", required = false) String mensaje,
            @RequestParam(name = "mensajeError", required = false) String mensajeError
    ){

        ModelAndView mav = new ModelAndView(listadoCitas);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        menuDto = menuService.reiniciarElementos();
        menuDto.setCitas("active");
        menuDto.setConsultarCitas("active");

        mav.addObject("menuDto",menuDto);


        mav.addObject("rolEspecial",sesionService.esRolEspecial(sesionService.obtenerRoles()));


        if(mensajeError!=null){
            mav.addObject("mensajeError", mensajeError);
        }

        if(mensaje!=null){
            mav.addObject("mensaje", mensaje);
        }

        mav.addObject("citaIdDto", citaIdDto);
        mav.addObject("centro", listadoService.listarCentro());


        mav.addObject("citas", listadoService.listarCitasPorRol(filtroSeleccionado));
        mav.addObject("titulo", mensajesService.getMensaje());
        return mav;
    }

    @PostMapping("/area/citas/borrar")
    public void borrarCitas(@ModelAttribute("citaIdDto") CitaIdDto citaIdDto,HttpServletResponse response) throws IOException {

        ModelAndView mav = new ModelAndView(listadoCitas);

        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        menuDto = menuService.reiniciarElementos();
        menuDto.setCitas("active");
        menuDto.setConsultarCitas("active");

        mav.addObject("menuDto",menuDto);

        // cancela la cita
        boolean citaCancelada = citasService.cancelarCita(citaIdDto.getId());

        // si se pudo cancelar
        if(citaCancelada){

            // libera la hora disponible del doctor
            citasService.guardarCitaCispoinible(citaIdDto);
            response
                    .sendRedirect("/area/citas/totales?mensaje=" + mensajesService.getMensaje());
        }else{
            response
                    .sendRedirect("/area/citas/totales?mensajeError=" +
                            excepcionesService.getMensajeError().get("cancelarCita"));

        }

    }


}
