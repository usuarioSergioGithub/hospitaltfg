package com.eep.hospital.controller;


import com.eep.hospital.dto.ClienteModifDto;
import com.eep.hospital.dto.MenuDto;
import com.eep.hospital.dto.PersonalModifDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.PersonalResponse;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@Controller
@RequestMapping("/consulta")
public class ConsultaDatosController {

    // ***************** Servicios *********************

    // -------- Servicio para reemplazar datos
    @Autowired
    @Qualifier("datosServiceImpl")
    DatosService datosService;

    @Autowired
    @Qualifier("modificacionServiceImpl")
    ModificacionService modificacionService;

    // -------- Servicio para calcular la letra del DNI
    @Autowired
    @Qualifier("validarDniServiceImpl")
    ValidarDniService validarDniService;

    // -------- Servicio para el listado
    @Autowired
    @Qualifier("listadoServiceImpl")
    ListadoService listadoService;

    // -------- Servicio para controlar la ubicación del usuario en todo momento
    @Autowired
    @Qualifier("opcionesServiceImpl")
    OpcionesService opcionesService;

    @Autowired
    @Qualifier("comprobacionesServiceImpl")
    ComprobacionesService comprobacionesService;

    // -------- Servicio para el borrado
    @Autowired
    @Qualifier("borradoServiceImpl")
    BorradoService borradoService;

    // -------- Servicio para controlar los errores
    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    @Autowired
    @Qualifier("personalModifDto")
    PersonalModifDto personalModifDto;

    // ***************** application.properties ********************

    // -------- Plantillas HTML -------------
    @Value("${plantilla.listadoPersonal}")
    private String listadoPersonal;

    @Value("${plantilla.listadoCitas}")
    private String listadoCitas;

    @Value("${plantilla.listadoPacientes}")
    private String listadoPacientes;

    @Value("${plantilla.confirmarBaja}")
    private String confirmarBaja;

    @Value("${plantilla.listadoFacturas}")
    private String listadoFacturas;

    // ------- Posibles ubicaciones del usuario ----------
    @Value("${consulta.personal}")
    private String consultaPersonal;

    @Value("${consulta.citas}")
    private String consultaCitas;

    @Value("${consulta.pacientes}")
    private String consultaPacientes;

    // ------ Identificadores ------

    @Value("${identificador.paciente}")
    private String entidadPaciente;

    @Value("${identificador.personal}")
    private String entidadPersonal;

    @Value("${identificador.cita}")
    private String entidadCita;

    @Autowired
    @Qualifier("comprobarFormularioImpl")
    CompFormService comprobarFormulario;

    // ***************** Componentes *********************

    // --------- Componente para el menu ---------

    @Autowired
    private MenuDto menuDto;

    @Autowired
    @Qualifier("clienteModifDto")
    ClienteModifDto clienteModifDto;

    @Autowired
    @Qualifier("menuServiceImpl")
    MenuService menuService;

    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;


    // ***************** Flujo de la aplicación *****************


    @GetMapping("/personal")
    public ModelAndView listarPersonal(
            @RequestParam(name = "mensaje", required = false) String mensaje,
            @RequestParam(name = "mensajeError", required = false) String mensajeError
    ){

        opcionesService.setFormulario(consultaPersonal);
        opcionesService.setIdentificador(entidadPersonal);

        ModelAndView mav = new ModelAndView(listadoPersonal);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        menuDto = menuService.reiniciarElementos();
        menuDto.setConsulta("active");
        menuDto.setConsultarPersonal("active");
        mav.addObject("menuDto", menuDto);


        List<PersonalResponse> personalDatosJustos = listadoService.listarPersonal(null, null);


        if (mensaje!=null){
            mav.addObject("mensaje", mensaje);
        }

        if (mensajeError!=null){
            mav.addObject("mensajeError", mensajeError);
        }

        mav.addObject("personalModifDto", personalModifDto);
        mav.addObject("personal",personalDatosJustos);//

        return mav;
    }

    @GetMapping("/pacientes")
    public ModelAndView listarPacientes(
            @RequestParam(name = "mensaje", required = false) String mensaje,
            @RequestParam(name = "mensajeError", required = false) String mensajeError
    ){

        opcionesService.setFormulario(consultaPacientes);
        opcionesService.setIdentificador(entidadPaciente);

        ModelAndView mav = new ModelAndView(listadoPacientes);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));

        menuDto = menuService.reiniciarElementos();
        menuDto.setConsulta("active");
        menuDto.setConsultarPacientes("active");

        if (mensaje!=null){
            mav.addObject("mensaje", mensaje);
        }

        if (mensajeError!=null){
            mav.addObject("mensajeError", mensajeError);
        }

        mav.addObject("menuDto", menuDto);

        mav.addObject("clienteDto", clienteModifDto);
        mav.addObject("pacientes", listadoService.listarPacientes(null, null));

        return mav;
    }

    @GetMapping("/pacientes/facturas")
    public ModelAndView listarPacientesPendientesPago(
            @RequestParam(name = "pagadas", required = false) boolean pagadas,
            @RequestParam(name = "sinPagar", required = false) boolean sinPagar
    ){

        opcionesService.setFormulario(consultaPacientes);

        ModelAndView mav = new ModelAndView(listadoFacturas);
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        menuDto = menuService.reiniciarElementos();
        menuDto.setConsulta("active");
        menuDto.setPacientesPendientes("active");
        mav.addObject("menuDto", menuDto);

        // No ha activado ningun filtro
        if(!pagadas && !sinPagar){
            mav.addObject("facturas", listadoService.listarTodasLasFacturas());
        }

        if (pagadas){
            mav.addObject("facturas", listadoService.listarFacturas(true));
        }

        if (sinPagar){
            mav.addObject("facturas", listadoService.listarFacturas(false));
        }

        return mav;
    }


    // Presentar el formulario para modificar un alumno en concreto
    @PostMapping("/modificarEmpleado")
    public ModelAndView modificarEmpleado(
            @Validated @ModelAttribute("personalModifDto") PersonalModifDto empleado,
            BindingResult result,
            HttpServletResponse response) throws IOException {

        menuDto = menuService.reiniciarElementos();
        menuDto.setConsulta("active");
        menuDto.setConsultarPersonal("active");

        ModelAndView mav = null;
        if (comprobarFormulario.tieneFallos(result)){

            mav = new ModelAndView(listadoPersonal);

            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("menuDto", menuDto);
            mav.addObject("personalModifDto", personalModifDto);
            mav.addObject("personal", listadoService.listarPersonal(null, null));

            return mav;
        }

        if (!empleado.getPerfilUsuario().getRol().equals("doctor")
                && !validarDniService.CalcularLetra(empleado.getDniNColegiado())
        ){

            mav = new ModelAndView(listadoPersonal);
            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("menuDto", menuDto);
            mav.addObject("mensajeError", "El DNI no es válido.");
            mav.addObject("personalModifDto", personalModifDto);
            mav.addObject("personal", listadoService.listarPersonal(null, null));

            return mav;
        }

        Personal empleadoAModificar = datosService.asignarDatos(
                empleado,
                listadoService.listarPersonalCompleto()
        );

        boolean empleadoModificado = modificacionService.modificarEmpleado(empleadoAModificar);

        if (empleadoModificado){
            response.sendRedirect("/consulta/personal?mensaje=" + mensajesService.getMensaje());
        }else{
            response.sendRedirect("/consulta/personal?mensajeError=" +
                    excepcionesService.getMensajeError().get("modificarEmpleado"));
        }
        //aqui buscar por ese dni para rellenar los datos que falten
        return mav;
    }


    @PostMapping("/modificarCliente")
    public ModelAndView modificarCliente(
            @Validated @ModelAttribute("clienteDto") ClienteModifDto clienteDto,
            BindingResult result,
            HttpServletResponse response) throws IOException {

        menuDto = menuService.reiniciarElementos();
        menuDto.setConsulta("active");
        menuDto.setConsultarPacientes("active");

        ModelAndView mav = null;
        if (comprobarFormulario.tieneFallos(result)){

            mav = new ModelAndView(listadoPacientes);

            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("menuDto", menuDto);


            mav.addObject("noValido", clienteDto.getDni());
            mav.addObject("clienteDto", clienteDto);
            mav.addObject("pacientes", listadoService.listarPacientes(null, null));

            return mav;
        }


        ClienteEntity cliente = datosService.asignarDatosCliente(
                clienteDto,
                listadoService.listarClienteCompleto()
        );

        boolean clienteModificado = modificacionService.modificarCliente(cliente);

        if (clienteModificado){
            response.sendRedirect("/consulta/pacientes?mensaje=" + mensajesService.getMensaje());
        }else{
            response.sendRedirect("/consulta/pacientes?mensajeError=" +
                    excepcionesService.getMensajeError().get("modificarCliente"));
        }

        return mav;
    }


    // Al confirmar la baja, puede querer borrar los clientes, el personal o anular una cita
    @GetMapping("/confirmarBaja")
    public ModelAndView confirmarBaja(
            @RequestParam(name="empleadosBorrado", required = false) List<String> empleadosBorrado,
            @RequestParam(name="pacientesBorrado", required = false) List<String> pacientesBorrado,
            @RequestParam(name="citasBorrado", required = false) List<String> citasBorrado){

        ModelAndView mav = null;

        if(empleadosBorrado!=null){
            boolean quedanEmpleadosPorRol = comprobacionesService.quedanEmpleadosPorRol(empleadosBorrado);

            if(!quedanEmpleadosPorRol){
                mav = new ModelAndView(listadoPersonal);

                menuDto = menuService.reiniciarElementos();

                menuDto.setConsulta("active");
                menuDto.setConsultarPersonal("active");


                mav.addObject("mensajeError", mensajesService.getMensaje());
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("menuDto",menuDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("personal", listadoService.listarPersonal(null, null));

                return mav;
            }
        }

        if(empleadosBorrado ==null && opcionesService.getFormulario().equals(consultaPersonal)) {

            mav = new ModelAndView(listadoPersonal);

            menuDto = menuService.reiniciarElementos();
            menuDto.setConsulta("active");
            menuDto.setConsultarPersonal("active");
            mav.addObject("personalModifDto", personalModifDto);
            mav.addObject("menuDto",menuDto);
            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("personal", listadoService.listarPersonal(null, null));

            return mav;
        }


        if(pacientesBorrado==null && opcionesService.getFormulario().equals(consultaPacientes)) {

            mav = new ModelAndView(listadoPacientes);

            menuDto = menuService.reiniciarElementos();
            menuDto.setConsulta("active");
            menuDto.setConsultarPacientes("active");
            mav.addObject("personalModifDto", personalModifDto);
            mav.addObject("menuDto",menuDto);
            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("pacientes", listadoService.listarPacientes(null, null));

            return mav;
        }

        if(citasBorrado==null && opcionesService.getFormulario().equals(consultaCitas)) {

            mav = new ModelAndView(listadoCitas);

            menuDto = menuService.reiniciarElementos();
            menuDto.setCitas("active");
            menuDto.setConsultarCitas("active");
            mav.addObject("personalModifDto", personalModifDto);
            mav.addObject("menuDto",menuDto);
            mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
            mav.addObject("citas", listadoService.listarCitas(null, null));

            return mav;
        }


        //pacientes
        if(opcionesService.getIdentificador().equals(entidadPaciente)) {
            List<String> pacientesBorrar = listadoService.obtenerPacientesABorrar(pacientesBorrado);
            String mensajeError = excepcionesService.getMensajeError().get("obtenerPacientesABorrar");

            // Si no hay coches para borrar y ocurrio una excepcion
            if (pacientesBorrar==null && mensajeError!=null) {

                mav = new ModelAndView(listadoPacientes);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPacientes("active");
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("menuDto",menuDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("mensajeError", mensajeError);


            }else {

                mav = new ModelAndView(confirmarBaja);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                opcionesService.setIdentificadores(pacientesBorrado);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPacientes("active");
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("menuDto",menuDto);
                mav.addObject("titulo", "Confirmar baja de pacientes.");
                mav.addObject("mensajeConfirmar", "Se van a eliminar los siguientes pacientes:");
                mav.addObject("item_menu", opcionesService.getItem_menu());
                mav.addObject("entidades", pacientesBorrar);

            }

            // personal
        }else if (opcionesService.getIdentificador().equals(entidadPersonal)){
            List<String> personalABorrar = listadoService.obtenerPersonalABorrar(empleadosBorrado);
            String mensajeError = excepcionesService.getMensajeError().get("obtenerPersonalABorrar");

            // Si no hay coches para borrar y ocurrio una excepcion
            if (personalABorrar==null && mensajeError!=null) {

                mav = new ModelAndView(listadoPersonal);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPersonal("active");


                mav.addObject("menuDto",menuDto);
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("mensajeError", mensajeError);


            }else {

                mav = new ModelAndView(confirmarBaja);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPersonal("active");

                opcionesService.setIdentificadores(empleadosBorrado);
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("menuDto",menuDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("titulo", "Confirmar baja de empleados.");
                mav.addObject("mensajeConfirmar", "Se van a eliminar los siguientes empleados:");
                mav.addObject("item_menu", opcionesService.getItem_menu());
                mav.addObject("entidades", personalABorrar);

            }
        }else {

            List<String> citasABorrar = listadoService.obtenerCitasABorrar(citasBorrado);
            String mensajeError = excepcionesService.getMensajeError().get("obtenerCitasABorrar");

            if (citasABorrar ==null && mensajeError!=null) {

                mav = new ModelAndView(listadoCitas);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarCitas("active");
                mav.addObject("personalModifDto", personalModifDto);
                mav.addObject("menuDto",menuDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("mensajeError", mensajeError);


            }else {
                // Si no hay coches para borrar y ocurrio una excepcion

                mav = new ModelAndView(confirmarBaja);

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarCitas("active");

                opcionesService.setIdentificadores(citasBorrado);

                mav.addObject("menuDto",menuDto);
                mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
                mav.addObject("titulo", "Confirmar baja de citas.");
                mav.addObject("mensajeConfirmar", "Se van a eliminar las siguientes citas:");
                mav.addObject("item_menu", opcionesService.getItem_menu());
                mav.addObject("entidades", citasABorrar);

            }

        }

        return mav;
    }

    @GetMapping("borrar")
    public String borrar(Model model){

        model.addAttribute("personalModifDto", personalModifDto);
        model.addAttribute("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        // borrar pacientes
        if(opcionesService.getIdentificador().equals(entidadPaciente)) {

            String pacientesBorrado = borradoService.borrarPacientes(opcionesService.getIdentificadores());
            String mensajeError = excepcionesService.getMensajeError().get("borradoPacientes");

            // Si ocurre una excepción
            if (pacientesBorrado ==null && mensajeError!=null) {

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPacientes("active");

                model.addAttribute("menuDto",menuDto);

                model.addAttribute("pacientes", listadoService.listarPacientes(null, null));
                model.addAttribute("mensajeError", mensajeError);

                return listadoPacientes;
            }

            mensajesService.setMensaje(pacientesBorrado);

            return "redirect:pacientes?mensaje=" + pacientesBorrado;

            // borrar empleados
        }else if (opcionesService.getIdentificador().equals(entidadPersonal)){
            String personalBorrado = borradoService.borrarPersonal(opcionesService.getIdentificadores());
            String mensajeError = excepcionesService.getMensajeError().get("borradoPersonal");

            // Si ocurre una excepción
            if (personalBorrado ==null && mensajeError!=null) {

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarPersonal("active");

                model.addAttribute("menuDto",menuDto);

                model.addAttribute("personalModifDto", personalModifDto);
                model.addAttribute("personal", listadoService.listarPersonal(null, null));
                model.addAttribute("mensajeError", mensajeError);

                return listadoPersonal;
            }

            mensajesService.setMensaje(personalBorrado);

            return "redirect:personal?mensaje=" +personalBorrado;
        }else {

            String citasBorrado = borradoService.borrarCitas(opcionesService.getIdentificadores());
            String mensajeError = excepcionesService.getMensajeError().get("borradoCitas");

            // Si ocurre una excepción
            if (citasBorrado ==null && mensajeError!=null) {

                menuDto = menuService.reiniciarElementos();
                menuDto.setConsulta("active");
                menuDto.setConsultarCitas("active");

                model.addAttribute("menuDto",menuDto);

                model.addAttribute("citas", listadoService.listarCitas(null, null));
                model.addAttribute("mensajeError", mensajeError);

                return listadoCitas;
            }

            mensajesService.setMensaje(citasBorrado);

            return "redirect:citas";
        }

    }



}