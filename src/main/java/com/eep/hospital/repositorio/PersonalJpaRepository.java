package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Personal;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository ("personalJpaRepository")
public interface PersonalJpaRepository extends JpaRepository<Personal, Serializable> {

    // Buscar empleado por rol
    List<Personal> findByPerfilUsuarioRol(String rol);

    // Buscar empleado por nombre ordenado descendientemente (z-a)
    List<Personal> findAllByOrderByNombreDesc();

    // Buscar horario de todos los doctores por especialidad
    @Query("select p from Personal p where p.especialidad.nombreEspecialidad = ?1")
    List<Personal> horarioTodosLosDoctores(String especialidad);

    // Buscar horario de un doctor por especialidad
    @Query("select p from Personal p where p.dniNColegiado = ?1 and p.especialidad.nombreEspecialidad = ?2")
    List<Personal> horarioDoctor (String dniNColegiado, String especialidad);

    // Buscar empleado por su dni o nº de colegiado
    Personal findByDniNColegiado (String dniNColegiado);

    // Buscar empleado por correo
    Personal findByCorreo(String correo);

    @Transactional
    List<Personal> findByDniNColegiadoIn(List<String> dniNColegiadoPersonal);

    @Transactional
    List<Personal> deleteByDniNColegiadoIn(List<String> dniNColegiadoPersonal);

    /*@Transactional// ya que al ser una operacion mas delicada se necesita que se bloqueen las operaciones
    hasta que esta termina*/

}
