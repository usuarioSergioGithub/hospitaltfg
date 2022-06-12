package com.eep.hospital.repositorio;

import com.eep.hospital.entity.EspecialidadEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("especialidadJpaRepository")
public interface EspecialidadJpaRepository extends JpaRepository<EspecialidadEntity, Serializable> {


}
