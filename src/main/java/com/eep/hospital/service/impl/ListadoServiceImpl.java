package com.eep.hospital.service.impl;

import com.eep.hospital.component.*;
import com.eep.hospital.entity.Centro;
import com.eep.hospital.entity.Cita;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.repositorio.*;
import com.eep.hospital.response.*;
import com.eep.hospital.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service("listadoServiceImpl")
public class ListadoServiceImpl implements ListadoService {


    // -------- Servicio para controlar los errores
    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;

    // -------- Servicio para obtener el correo del usuario que inició sesión
    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;

    //
    @Autowired
    @Qualifier("filtrosServiceImpl")
    FiltrosService filtrosService;


    // -------- Roles
    @Value("${rol.departamento.medicina}")
    private String rol_medicina;

    @Value("${rol.departamento.inventario}")
    private String rol_adminInventario;

    @Value("${rol.departamento.comunicacion}")
    private String rol_operador;

    @Value("${rol.usuario}")
    private String rol_estandar;

    @Value("${rol.creador}")
    private String rol_creadorWeb;


    // ============ CONVERSORES ============
    @Autowired
    @Qualifier("conversoresAutoridComponent")
    ConversoresAutoridComponent conversoresAutoridComponent;

    @Autowired
    @Qualifier("conversoresIntervComponent")
    ConversoresIntervComponent conversoresIntervComponent;

    @Autowired
    @Qualifier("conversoresClienteComponent")
    ConversoresClienteComponent conversoresClienteComponent;

    @Autowired
    @Qualifier("conversoresCitasComponent")
    ConversoresCitasComponent conversoresCitasComponent;

    @Autowired
    @Qualifier("conversoresFacturasComponent")
    ConversoresFacturasComponent conversoresFacturasComponent;

    @Autowired
    @Qualifier("conversoresInvComponent")
    ConversoresInvComponent conversoresInvComponent;

    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;

    @Autowired
    @Qualifier("conversoresEspecialidadComponent")
    ConversoresEspecialidadComponent conversoresEspecialidadComponent;

    @Autowired
    @Qualifier("conversoresPrestacionComponent")
    ConversoresPrestacionComponent conversoresPrestacionComponent;

    // ============ REPOSITORIOS ============
    @Autowired
    @Qualifier("clienteIntervDoctorJpaRepository")
    ClienteIntervDoctorJpaRepository clienteIntervDoctorJpaRepository;

    @Autowired
    @Qualifier("personalJpaRepository")
    PersonalJpaRepository personalJpaRepository;

    @Autowired
    @Qualifier("clienteJpaRepository")
    ClienteJpaRepository clienteJpaRepository;

    @Autowired
    @Qualifier("especialidadJpaRepository")
    EspecialidadJpaRepository especialidadJpaRepository;

    @Autowired
    @Qualifier("prestacionJpaRepository")
    PrestacionJpaRepository prestacionJpaRepository;

    @Autowired
    @Qualifier("citasJpaRepository")
    CitasJpaRepository citasJpaRepository;

    @Autowired
    @Qualifier("usuarioJpaRepository")
    UsuarioJpaRepository usuarioJpaRepository;

    @Autowired
    @Qualifier("facturasJpaRepository")
    FacturasJpaRepository facturasJpaRepository;

    @Autowired
    @Qualifier("inventarioJpaRepository")
    InventarioJpaRepository inventarioJpaRepository;

    @Autowired
    @Qualifier("centroJpaRepository")
    CentroJpaRepository centroJpaRepository;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    @Override
    public Centro listarCentro() {
        return centroJpaRepository.findByNombre("Amikeha");
    }

    @Override
    public List<IntervencionesResponse> listarIntervenciones(String paciente, String doctor) {


        if(paciente!=null){
            return conversoresIntervComponent.convertirIntervencionARespuestas(
                    clienteIntervDoctorJpaRepository.findByClienteCorreo(paciente)
            );

        }

        if (doctor!=null){
            return conversoresIntervComponent.convertirIntervencionARespuestas(
                    clienteIntervDoctorJpaRepository.findByPersonalCorreo(doctor)
            );
        }

        return conversoresIntervComponent.convertirIntervencionARespuestas(
                clienteIntervDoctorJpaRepository.findAll()
        );

    }

    @Override
    public List<PersonalResponse> listarPersonal(String rol, String especialidad) {

        if (rol!=null){
            return conversoresPersonalComponent.convertirPersonalARespuestas(
                    personalJpaRepository.findByPerfilUsuarioRol(rol)
            );

        }

        if (especialidad!=null){
            // si el correo del usuario que ha iniciado sesion coincide con alguno, lo quitas
            List<Personal> empleados = personalJpaRepository.horarioTodosLosDoctores(especialidad);

            return filtrosService.quitarEmpleadoResponse(
                            empleados,
                            sesionService.obtenerCorreoUsuario(false)
                    );
        }

        return conversoresPersonalComponent
                .convertirPersonalARespuestas(
                        personalJpaRepository.findAllByOrderByNombreDesc()
                );

    }


    @Override
    public List<PersonalResponseCompleto> listarPersonalCompleto() {

        return conversoresPersonalComponent.convertirPersonalCompARespuestas(
                        personalJpaRepository.findAll()
        );

    }

    @Override
    public List<ClienteResponseCompleto> listarClienteCompleto() {

        return conversoresClienteComponent.convertirClienteCompARespuestas(
                clienteJpaRepository.findAll()
        );

    }



    @Override
    public List<CitaResponse> listarCitasPorRol(boolean filtroSeleccionado) {

        boolean esRolEstandar = sesionService.esRolEstandar();
        boolean esRolCreador = sesionService.esRolEspecial(Arrays.asList(rol_creadorWeb));
        boolean esRolDoctor = sesionService.esRolEspecial(Arrays.asList(rol_medicina));
        boolean esRolOperador = sesionService.esRolEspecial(Arrays.asList(rol_operador));
        boolean esRolAdminInv = sesionService.esRolEspecial(Arrays.asList(rol_adminInventario));

        Personal empleado = personalJpaRepository.findByCorreo(sesionService.obtenerCorreoUsuario(false));

        ClienteEntity cliente = clienteJpaRepository.findByCorreo(sesionService.obtenerCorreoUsuario(false));

        // Si soy yo mismo he puesto mirar todas las citas, este rol es solo para pruebas
        if (esRolCreador && !filtroSeleccionado){

            mensajesService.setMensaje("Listado de todas las citas");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.findAll()
            );
        }

        if (esRolCreador && filtroSeleccionado){

            mensajesService.setMensaje("Hola, estas son la/s citas que pidió.");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.buscarCitasDelEmpleadoComoPaciente(empleado.getDniNColegiado())
            );
        }

        // Puede haber iniciado sesion con rol de doctor y buscar sus citas para otras personas
        if (esRolDoctor && !filtroSeleccionado){

            List<Cita> citas = citasJpaRepository.buscarCitasDelEmpleado(empleado.getDniNColegiado());

            mensajesService.setMensaje("Hola, estas son la/s citas que tiene con los pacientes.");

            // - lista las citas que tiene con los pacientes
            return conversoresCitasComponent.convertirCitaARespuestas(
                    citas
            );
        }

        // puede haber iniciado sesion con rol de doctor, pero el mismo pidió cita y quiere buscar sus proximas citas
        if (esRolDoctor && filtroSeleccionado){

            mensajesService.setMensaje("Hola, estas son la/s citas que pidió.");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.buscarCitasDelEmpleadoComoPaciente(empleado.getDniNColegiado())
            );
        }

        // puede haber iniciado sesion con rol de operador y buscar todas las citas existentes
        if (esRolOperador && !filtroSeleccionado){

            mensajesService.setMensaje("Listado de todas las citas");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.findAll()
            );
        }

        // puede haber iniciado sesion con rol de operador, pero el mismo pidió cita y quiere buscar sus proximas citas
        if (esRolOperador && filtroSeleccionado){

            mensajesService.setMensaje("Hola, estas son las citas que pidió.");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.buscarCitasDelEmpleadoComoPaciente(empleado.getDniNColegiado())
            );
        }

        //puede haber iniciado sesion con rol de adminInv y buscar sus proximas citas
        if (esRolAdminInv){

            mensajesService.setMensaje("Hola, estas son las citas que pidió.");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.buscarCitasDelEmpleadoComoPaciente(empleado.getDniNColegiado())
            );
        }

        // puede haber iniciado sesion con rol de estandar y buscar sus proximas citas
        if (esRolEstandar){
            // *** Aqui el boton de "Mis citas" se ocultaría (crear ese boton)

            mensajesService.setMensaje("Hola, estas son las citas que pidió.");

            return conversoresCitasComponent.convertirCitaARespuestas(
                    citasJpaRepository.buscarCitasDelPaciente(cliente.getDni())
            );
        }

        // *** el rol de director se ignora ya que no tiene sentido que el directos quiera consultar todas las citas existentes

        return null;
    }

    @Override
    public List<ClienteResponse> listarPacientes(String dni, String correo) {

        if (dni!=null){
            return conversoresClienteComponent.convertirClienteARespuestas(Arrays.asList(clienteJpaRepository.findByDni(dni)));
        }

        if (correo!=null){

            conversoresClienteComponent.convertirClienteARespuesta(clienteJpaRepository.findByCorreo(correo));

        }

        return conversoresClienteComponent.convertirClienteARespuestas(clienteJpaRepository.findAll());
    }

    @Override
    public Personal obtenerEmpleadoPorDni(String dniNColegiado) {

        return personalJpaRepository.findByDniNColegiado(dniNColegiado);
    }

    @Override
    public List<FacturasResponse> listarFacturas(boolean pagada) {
        return conversoresFacturasComponent.convertirFacturaARespuestas(
                facturasJpaRepository.findByPagada(pagada)
        );
    }

    @Override
    public List<FacturasResponse> listarTodasLasFacturas() {
        return conversoresFacturasComponent.convertirFacturaARespuestas(
                facturasJpaRepository.findAll()
        );
    }

    @Override
    public List<CitaResponse> listarCitas(String correoPaciente, String fecha) {

        List<Cita> citas = citasJpaRepository.buscarCitasPorCorreo(correoPaciente);

        if(correoPaciente!=null){
            return conversoresCitasComponent.convertirCitaARespuestas(
                    citas
            );
        }

        if (fecha!=null){
            //buyscas por fecha
            return null;
        }

        return conversoresCitasComponent.convertirCitaARespuestas(citasJpaRepository.findAll());
    }

    @Override
    public List<EspecialidadResponse> obtenerTodasLasEspecialidades(String especialidad) {

        if (especialidad==null){
            return conversoresEspecialidadComponent.convertirEspecialidadARespuestas(especialidadJpaRepository.findAll());
        }

        List<Personal> p = personalJpaRepository.horarioTodosLosDoctores(especialidad);

        return conversoresEspecialidadComponent.convertirEspecialidadARespuestas(
                null
        );

    }

    @Override
    public List<PrestacionesResponse> obtenerTodasLasPrestaciones(String especialidad) {

        if (especialidad==null){
            return conversoresPrestacionComponent.convertirPrestacionARespuestas(prestacionJpaRepository.findAll());
        }

        return conversoresPrestacionComponent.convertirPrestacionARespuestas(
                prestacionJpaRepository.findByEspecialidadNombreEspecialidad(especialidad)
        );

    }

    @Override
    public List<String> obtenerPersonalABorrar(List<String> personalDNINcolegiado) {
        try {

            List<Personal> personal = personalJpaRepository.findByDniNColegiadoIn(personalDNINcolegiado);

            List<String> mostrarDatos = new ArrayList<>();

            Personal empleado;

            for (int i = 0; i < personal.size(); i++) {
                empleado = personal.get(i);
                mostrarDatos.add(" - " + empleado.getNombre() + ", con DNI " + empleado.getDniNColegiado() + ".");
            }




            return mostrarDatos;
        }catch (RuntimeException e) {
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("obtenerPersonalABorrar", "Ocurrió un error inesperado.");
            excepcionesService.setMensajeError(error);
            return null;
        }
    }


    @Override
    public List<String> obtenerPacientesABorrar(List<String> pacientesDNI) {
        try {

            List<ClienteEntity> pacientes = clienteJpaRepository.findByDniIn(pacientesDNI);

            List<String> mostrarDatos = new ArrayList<>();

            ClienteEntity paciente;

            for (int i = 0; i < pacientes.size(); i++) {
                paciente = pacientes.get(i);
                mostrarDatos.add(" - " + paciente.getNombre() + ", con DNI " + paciente.getDni() + ".");
            }

            return mostrarDatos;
        }catch (RuntimeException e) {
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("obtenerPacientesABorrar", "Ocurrió un error inesperado.");
            excepcionesService.setMensajeError(error);
            return null;
        }
    }

    @Override
    public List<String> obtenerCitasABorrar(List<String> citasFecha) {
        try {

            List<Cita> citas = citasJpaRepository.findByHoraIn(citasFecha);

            List<String> mostrarDatos = new ArrayList<>();

            Cita cita;

            for (int i = 0; i < citas.size(); i++) {
                cita = citas.get(i);
                mostrarDatos.add(" - Cita para el día " + cita.getFechaDisponible() + " a las " + cita.getHora() + ".");
            }

            return mostrarDatos;
        }catch (RuntimeException e) {
            HashMap<String, String> error = new HashMap<String, String>();
            error.put("obtenerCitasABorrar", "Ocurrió un error inesperado.");
            excepcionesService.setMensajeError(error);
            return null;
        }
    }

    @Override
    public List<InventarioResponse> obtenerInventario() {
        return conversoresInvComponent.convertirInvARespuestas(
                inventarioJpaRepository.findAll()
        );
    }
}
