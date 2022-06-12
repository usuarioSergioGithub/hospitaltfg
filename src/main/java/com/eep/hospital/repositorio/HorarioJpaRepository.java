package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Horario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("horarioJpaRepository")
public interface HorarioJpaRepository extends JpaRepository<Horario, Serializable> {


}
