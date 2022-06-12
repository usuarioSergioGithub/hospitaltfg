package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Cita;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository ("citasJpaRepository")
public interface CitasJpaRepository extends JpaRepository<Cita, Serializable> {

    // Obtener citas de un doctor
    @Query("select c from Cita c where c.empleadoDoctor.dniNColegiado = ?1")
    List<Cita> buscarCitasDelEmpleado(String dniNColegiado);

    // Obtener citas de un doctor como paciente
    @Query("select c from Cita c where c.empleadoPaciente.dniNColegiado = ?1")
    List<Cita> buscarCitasDelEmpleadoComoPaciente(String dniNColegiado);

    // Obtener citas de un paciente
    @Query("select c from Cita c where c.clientePaciente.dni = ?1")
    List<Cita> buscarCitasDelPaciente(String dni);

    // Obtener citas por correo
    @Query(value = "select * from cita inner join cliente where cliente.correo= ?1", nativeQuery = true)
    List<Cita> buscarCitasPorCorreo(String correo);

    // Obtener citas por horas
    @Transactional
    List<Cita> findByHoraIn(List<String> fechasCitas);

    // Borrar las horas seleccionadas
    @Transactional
    List<Cita> deleteByHoraIn(List<String> fechasCitas);

    // listar todas las citas
    List<Cita> findAll();

}
