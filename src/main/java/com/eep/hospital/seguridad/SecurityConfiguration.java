package com.eep.hospital.seguridad;


import com.eep.hospital.controller.errores.AccesoDenegado;
import com.eep.hospital.service.UsuarioServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.access.AccessDeniedHandler;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

	// ================== SERVICIOS ==================
	@Autowired
	private UsuarioServicio usuarioServicio;

	// ================== ROLES ==================
	@Value("${rol.departamento.medicina}")
	private String doctor_rol;

	@Value("${rol.departamento.comunicacion}")
	private String operador_rol;

	@Value("${rol.departamento.inventario}")
	private String adminInventario_rol;

	@Value("${rol.departamento.bd}")
	private String encargadoBasesDeDatos_rol;

	@Value("${rol.creador}")
	private String creador_rol;

	@Value("${rol.director}")
	private String director_rol;

	@Value("${rol.usuario}")
	private String estandar_rol;

	// ================== CONFIGURACION ==================

	// --- establecer con que se realizará el cifrado/descifrado
	@Bean
	public PasswordEncoder codificador() {
		return new BCryptPasswordEncoder();
	}

	// --- Configurar la clase para autenticar a los usuatrios
	@Bean
	public DaoAuthenticationProvider autenticador() {

		// Clase para la autenticacion
		DaoAuthenticationProvider auth = new DaoAuthenticationProvider();

		// establecer el servicio que obtendrá los usuarios de la base de datos
		auth.setUserDetailsService(usuarioServicio);

		// establecer el metodo para el cifrado y descifrado
		// (para que el loadbyusername del servicio anterior descifre la contraseña de la base de datos
		auth.setPasswordEncoder(codificador());
		return auth;
	}

	// --- Configurar cual será la clase customizada que maneje el acceso denegado
	// este Bean es opcional, pero sin él, se devolvería el codigo de estado 403 (acceso denegado)
	// y se vería una pagina de error que es la de spring por defecto
	@Bean
	public AccessDeniedHandler accesoDenegado(){
		return new AccesoDenegado();
	}

	// Metodo que se encarga de manejar la autenticacion de los usuarios
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {

		// Establecer cual será el autenticador
		auth
				.authenticationProvider(autenticador());
	}


	// --- Meétodo principal para controlar diversas operaciones en la web
	@Override
	protected void configure(HttpSecurity http) throws Exception {

		http
			// Autorizar las peticiones
			.authorizeRequests()

			// ================= Permisos para cualquier usuario aunque no haya iniciado sesión ================

				.antMatchers(
						"/",
						"/centro/quienesomos",
						"/contacta",
						"//cliente/alta**", // *** aqui mirar en el diagrama de casos de uso los
						// roles de usuario no registrado y a las url que puedan acceder ponerselas aqui
						// Y todos los archivos estáticos
						"/js/**",
						"/css/**",
						"/img/**")

				// a todo el mundo
				.permitAll()
			.and()

				// Establecer cual será la url para el inicio de sesión
				.formLogin()
				.loginPage("/login")//login
				.usernameParameter("correo")// parametro que obtendrá el loadbyusername del formulario
				.permitAll() // permitir a todo el mundo
				.defaultSuccessUrl("/")// especificar pagina a la que llevará el usuario despues del login

			.and()

				// Cuando se cierre sesión destruirá los datos del usuario almacenados en sesión
				.logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
				.invalidateHttpSession(true)
				.clearAuthentication(true)


				// Ruta a donde irá cuando se cierre sesión
				.logoutSuccessUrl("/login?logout")
				.permitAll()

			// ================= Permisos especiales ================
			.and()

				.authorizeRequests()

				// -------------- INTERVENCIONES --------------
				.antMatchers("/listar/intervenciones").hasAnyAuthority(doctor_rol, estandar_rol, encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/realizarpago").hasAnyAuthority(operador_rol,estandar_rol, creador_rol)

				// -------------- CENTRO --------------

				.antMatchers("/centro/listarinventario").hasAnyAuthority(adminInventario_rol, encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/modificarArticulo").hasAnyAuthority(adminInventario_rol, encargadoBasesDeDatos_rol, creador_rol)

				// -------------- MI ÁREA --------------
				// -> como el pedir cita los 3 tipos de usuario lo harán en la misma plantilla html no hara falta ningun filtro
				.antMatchers("/area/pedircita").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				.antMatchers("/area/recargardatos").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				.antMatchers("/area/mostrarCitas").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				.antMatchers("/guardarCita").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				.antMatchers("/area/citas/borrar").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				// -> a la misma url tendrían acceso los 3, pero porque posteriormente se comprobará quién inició sesión
				// y de ahi se mostrará la información pertinente
				.antMatchers("/area/citas/totales").hasAnyAuthority(operador_rol, estandar_rol, doctor_rol, creador_rol)

				// -------------- CONSULTAR DATOS --------------
				.antMatchers("/consulta/pacientes").hasAnyAuthority(director_rol, encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/consulta/personal").hasAnyAuthority(director_rol, encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/consulta/modificarEmpleado").hasAnyAuthority(encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/consulta/modificarCliente").hasAnyAuthority(encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/consulta/confirmarBaja").hasAnyAuthority(encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("borrar").hasAnyAuthority(encargadoBasesDeDatos_rol, creador_rol)

				.antMatchers("/consulta/pacientes/facturas").hasAnyAuthority(director_rol, encargadoBasesDeDatos_rol, creador_rol)


			.and()

				// especificar la clase para cuando se deniegue el acceso
				.exceptionHandling().accessDeniedHandler(accesoDenegado())
			.and()
				.csrf().disable();

	}
}






