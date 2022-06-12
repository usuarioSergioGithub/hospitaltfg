package com.eep.hospital.entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalTime;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Getter
@Setter
@Entity
@Table (name = "personal")
@NoArgsConstructor
@AllArgsConstructor
// Cuando se ejecute un delete, se actualiza el campo despedido a true
// Esto sería un borrado logico, que a diferencia del físico, el dato "está eliminado"
// pero se mantiene un historico
/*@SQLDelete(sql = "UPDATE personal SET despedido = true WHERE dniNColegiado=?")*/
/*@FilterDef(
		name = "filtroPersonal",
		parameters = @ParamDef(name = "estaDespedido", type = "boolean")
)
@Filter(
		name = "filtroPersonal",
		condition = "despedido = :estaDespedido"
)*/
/*@Where(clause = "despedido = false") // mostrara los que no esten despedidos*/
public class Personal {

	@Id
	@Column (name = "dniNColegiado")
	private String dniNColegiado;

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
	private int cp;

	@Column (name = "telFijo")
	private String telFijo;

	@Column (name = "movil")
	private String movil;

	@Column (name = "diaDisponible")
	private int diaDisponible;

	@Column (name = "disponibleDesde")
	private LocalTime disponibleDesde;

	@Column (name = "disponibleHasta")
	private LocalTime disponibleHasta;

	@OneToMany(mappedBy = "empleadoPaciente")
	private List<Cita> empleadoPaciente ;

	@OneToMany(mappedBy = "empleadoDoctor")
	private List<Cita> empleadoDoctor;

	private boolean despedido = Boolean.FALSE;


	// ================ RELACIONES ================

	// Cuando se borre un empleado, se borra la relacion
	@OneToMany(mappedBy = "personal", orphanRemoval = true)
	Set<ClienteIntervDoctor> relacion = new HashSet<>();

	// Cuando se borre un empleado, se borra tambien la cita
	// mappeo por el campo de empleadoDoctor y no id de la cita
	// porque se asocia la cita con el empleado
	@OneToMany(mappedBy = "empleadoDoctor", orphanRemoval = true)
	Set<Cita> citas;


	// En personal guardo la clave foránea del departamento
	// Hay varias personas (personal) en el mismo departamento
	@ManyToOne // Sin cascade, no quiero que se elimine la otra entidad
	@JoinColumn (referencedColumnName = "nombre")
	private Departamento departamento;

	// En personal guardo la clave foranea de perfil de usuario, es decir, el tipo de rol
	// Hay varias personas con el mismo rol
	@ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
	@JoinColumn (name = "rol")
	PerfilUsuario perfilUsuario;

	@ManyToOne// Sin cascade, no quiero que se elimine la otra entidad
	@JoinColumn (name = "especialidad")
	EspecialidadEntity especialidad;

	// Cuando elimine un empleado quiero que se elimine su usuario
	@ManyToOne (cascade = CascadeType.REMOVE)
	@JoinColumn (name = "correoUsuario")
	UsuarioEntity usuario;

	// Sin cascade, no quiero que se elimine la otra entidad
	@ManyToMany (fetch = FetchType.EAGER)  //(cascade = CascadeType.REMOVE) // actualizar los campos relacionados
	@JoinTable( // establecer:

			name ="citas_disponibles", // el nombre de la tercera tabla para la relacion
			joinColumns = @JoinColumn ( name = "dniNColegiado"), // la clave primaria de esta entidad
			inverseJoinColumns = @JoinColumn (name = "hora") // la clave primaria de la entidad relacionada
	)
	private Set<Horario> horarios;

}
