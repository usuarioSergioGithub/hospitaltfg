package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresAutoridComponent;
import com.eep.hospital.entity.UsuarioEntity;
import com.eep.hospital.repositorio.UsuarioJpaRepository;
import com.eep.hospital.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UsuarioServicioImpl implements UsuarioServicio {

	// =============== SERVICIOS ===============
	@Autowired
	@Qualifier("usuarioJpaRepository")
	private UsuarioJpaRepository usuarioJpaRepository;

	// =============== CONVERSORES ===============
	@Autowired
	@Qualifier("conversoresAutoridComponent")
    ConversoresAutoridComponent conversoresComponent;

	
	public UsuarioServicioImpl(UsuarioJpaRepository usuarioJpaRepository) {
		super();
		this.usuarioJpaRepository = usuarioJpaRepository;
	}


	// --- Método que se ha especificado en la configuracion de seguridad para la autenticacion
	// de usuarios. Como se especificó en la linea 120 de la clase, el campo que utilizará del
	// login será el correo
	@Override
	public UserDetails loadUserByUsername(String correo) throws UsernameNotFoundException {

		// Obtiene el usuario de la base de datos
		UsuarioEntity usuario = usuarioJpaRepository.findByCorreoUsuario(correo);

		// Si no existe lanzará una excepción que se le mostrará al usuario
		if(usuario == null) {
			throw new UsernameNotFoundException("Usuario o password inválidos");
		}

		System.out.println(usuario.getPerfilUsuario().getRol());

		// Si existe, creará un nuevo usuario en sesión y establecera sus roles
		return new User(
				usuario.getCorreoUsuario(),
				usuario.getPassword(),
				conversoresComponent.mapearAutoridades(usuario.getPerfilUsuario()));
	}
}
