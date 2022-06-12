package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresClienteComponent;
import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.repositorio.ClienteJpaRepository;
import com.eep.hospital.repositorio.PersonalJpaRepository;
import com.eep.hospital.response.ClienteResponse;
import com.eep.hospital.response.IntervencionesResponse;
import com.eep.hospital.service.ComprobacionesService;
import com.eep.hospital.service.ExcepcionesService;
import com.eep.hospital.service.ListadoService;
import com.eep.hospital.service.MensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Service;

import java.util.*;

@Service("comprobacionesServiceImpl")
public class ComprobacionesServiceImpl implements ComprobacionesService {

	@Autowired
	@Qualifier("clienteJpaRepository")
	ClienteJpaRepository clienteJpaRepository;

	@Autowired
	@Qualifier("personalJpaRepository")
	PersonalJpaRepository personalJpaRepository;

	@Autowired
	@Qualifier("excepcionesServiceImpl")
	ExcepcionesService excepcionesService;
	
	@Autowired
	@Qualifier("conversoresClienteComponent")
	ConversoresClienteComponent conversoresClienteComponent;

	@Autowired
	@Qualifier("listadoServiceImpl")
	ListadoService listadoService;

	@Autowired
	@Qualifier("mensajesServiceImpl")
	MensajesService mensajesService;

	@Value("${rol.departamento.medicina}")
	private String rolDoctor;

	@Value("${rol.departamento.bd}")
	private String rolBd;

	@Value("${rol.usuario}")
	private String rolEstandar;

	@Value("${rol.creador}")
	private String rolCreador;

	@Override
	public ClienteResponse existeCliente(ClienteDto clienteDto){
		try {

			ClienteResponse clienteResponse = conversoresClienteComponent.convertirClienteARespuesta(
					clienteJpaRepository.findByDni(clienteDto.getDni()
			));

			return (clienteResponse != null) ? clienteResponse : null;

		} catch (RuntimeException e) {

			HashMap<String, String> error = new HashMap<String, String>();
			error.put("compDni",
					"Ocurrió un error y no se pudo dar de alta al cliente con DNI " + clienteDto.getDni());
			excepcionesService.setMensajeError(error);
			return null;

		}

	}

	@Override
	public boolean existeCorreoCliente(ClienteDto clienteDto) {
		try {

			ClienteEntity clienteBD = clienteJpaRepository.findByCorreo(clienteDto.getCorreo());
			Personal personalBD = personalJpaRepository.findByCorreo(clienteDto.getCorreo());

			return (clienteBD != null || personalBD != null) ? true : false;

		} catch (RuntimeException e) {

			HashMap<String, String> error = new HashMap<>();
			error.put("existeCorreoCliente",
					"Ocurrió un error y no se pudo verificar los datos.");
			excepcionesService.setMensajeError(error);
			return true;

		}
	}

	// Comprobar el rol del usuario que inició sesión
	@Override
	public List<IntervencionesResponse> usuarioAutenticado(Authentication auth) {

		String usuario = auth.getName();
		Collection<? extends GrantedAuthority> roles = auth.getAuthorities();

		for (GrantedAuthority rol: roles) {

			// Si es el creador o el encargado de la base de datos lista todas
			if (rol.getAuthority().equals(rolCreador) || rol.getAuthority().equals(rolBd)){

				mensajesService.setMensaje("Listado de todas las intervenciones");
				return listadoService.listarIntervenciones(null, null);

			// Si es un usuario, lista sus intervenciones
			}else if (rol.getAuthority().equals(rolEstandar)){

				mensajesService.setMensaje("Listado de sus próximas intervenciones");
				return listadoService.listarIntervenciones(usuario, null);

				// Si es un doctor, listara las intervenciones de ese doctor
			} else if (rol.getAuthority().equals(rolDoctor)){

				mensajesService.setMensaje("Estos son los pacientes a los que tendrá que intervenir");
				return listadoService.listarIntervenciones(null, usuario);

				//si llega hasta aquí puede ser una brecha de seguridad y no listaría nada
			} else {

				return null;

			}

		}
		return null;
	}

	@Override
	public boolean quedanEmpleadosPorRol(List<String> empleadosBorrado) {


		//busco todos esos identificadores para obtenerlos de la base de datos
		List<Personal> personalABorrar = personalJpaRepository.findByDniNColegiadoIn(empleadosBorrado);

		//por otra parte listo todos los existentes
		List<Personal> personal = personalJpaRepository.findAll();

		//de todos los existentes saco el rol
		HashMap<String, Integer> rolesBD = calcNumRoles(personal);

		// De los que se van a borrar saco el rol
		HashMap<String, Integer> rolesABorrar = calcNumRoles(personalABorrar);

		/*resto:
		numero de roles obtenido de los totales
		-
		numero de roles obtenido de los que se van a borrar*/


		for (Map.Entry rolBd : rolesBD.entrySet()) {

			//Obtengo la cantidad de empleados con ese rol de la bd
			int nEmpBd = (int) rolBd.getValue();

			for (Map.Entry rolABorrar : rolesABorrar.entrySet()) {

				// Si coincide el rol
				if (rolBd.getKey().equals(rolABorrar.getKey())){

					int nEmpBorrar = (int) rolABorrar.getValue();

					// resta y retorna false si van a quedar en 0
					if(nEmpBd-nEmpBorrar==0){

						mensajesService.setMensaje("Siempre debe haber mínimo 1 empleado por rol.");
						return false;
					}

				}

			}
		}

		/*// si llega hasta aqui retorna true

		si no va a quedar ninguno de rol especifico no se puede borrar

		muestra un mensaje de que el empleado de la linea x no se puede borrar
			porque sino no quedaría ninguno con ese rol*/

		return true;
	}


	private HashMap<String, Integer> calcNumRoles(List<Personal> personal){
		HashMap<String, Integer> numeroRoles = new HashMap<>();

		for (Personal emplBD: personal) {

			String rol = emplBD.getPerfilUsuario().getRol();

			if(numeroRoles.containsKey(rol)){

				Integer mas1 = numeroRoles.get(rol);

				mas1++;

				numeroRoles.put(rol, mas1);
			}else {
				numeroRoles.put(rol, 1);
			}

		}

		return numeroRoles;
	}
}
