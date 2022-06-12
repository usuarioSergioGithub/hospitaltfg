package com.eep.hospital.repositorio;

import com.eep.hospital.entity.PerfilUsuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("rolesJpaRepository")
public interface RolesJpaRepository extends JpaRepository<PerfilUsuario, Serializable> {

}
