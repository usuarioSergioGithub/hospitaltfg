package com.eep.hospital.repositorio;

import com.eep.hospital.entity.PrestacionesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.util.List;

@Repository ("prestacionJpaRepository")
public interface PrestacionJpaRepository extends JpaRepository<PrestacionesEntity, Serializable> {

    // Buscar las prestaciones de una especialidad
    List<PrestacionesEntity> findByEspecialidadNombreEspecialidad(String nombreEspecialidad);

}
