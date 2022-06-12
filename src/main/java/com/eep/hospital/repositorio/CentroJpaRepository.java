package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Centro;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("centroJpaRepository")
public interface CentroJpaRepository extends JpaRepository<Centro, Serializable> {

    Centro findByNombre(String nombre);
}
