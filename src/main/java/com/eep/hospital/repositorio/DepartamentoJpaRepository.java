package com.eep.hospital.repositorio;

import com.eep.hospital.entity.Departamento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("departamentoJpaRepository")
public interface DepartamentoJpaRepository extends JpaRepository<Departamento, Serializable> {

}
