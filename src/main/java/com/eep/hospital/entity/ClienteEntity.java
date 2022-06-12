package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "cliente")
public class ClienteEntity{

	@Id
	@Column (name = "dni")
	private String dni;

	@Column (name = "nombre")
	private String nombre;

	@Column (name = "correo", unique = true)
	private String correo;

	@Column (name = "apellidos")
	private String apellidos;

	@Column (name = "direccion")
	private String direccion;

	@Column (name = "localidad")
	private String localidad;

	@Column (name = "provincia")
	private String provincia;

	@Column (name = "cp")
	private String cp;

	@Column (name = "telFijo")
	private String telFijo;

	@Column (name = "movil")
	private String movil;


	// ================ RELACIONES ================

	@OneToMany(mappedBy = "cliente", orphanRemoval = true)
	Set<ClienteIntervDoctor> relacion = new HashSet<>();

	// Cuando se borre un empleado, se borra tambien la cita
	// mappeo por el campo de empleadoDoctor y no id de la cita
	// porque se asocia la cita con el empleado
	@OneToMany(mappedBy = "clientePaciente", orphanRemoval = true)
	Set<Cita> citas;

	// En cliente guardo la clave foranea de perfil de usuario, es decir, el tipo de rol
	// Hay varios con el mismo rol (al ser un cliente, siempre tendrá el mismo tipo de rol)
	@ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
	//@Cascade(org.hibernate.annotations.CascadeType.DELETE)
	@JoinColumn (name = "rol")
	PerfilUsuario perfilUsuario;

	// Si elimino un cliente, quiero que se elimine su usuario
	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "correoUsuario")
	UsuarioEntity usuario;
}