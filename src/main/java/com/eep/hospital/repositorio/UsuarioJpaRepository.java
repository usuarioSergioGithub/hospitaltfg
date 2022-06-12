package com.eep.hospital.repositorio;

import com.eep.hospital.entity.UsuarioEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository("usuarioJpaRepository")
public interface UsuarioJpaRepository extends JpaRepository<UsuarioEntity, Serializable> {

    // Buscar un usuario por su correo
    UsuarioEntity findByCorreoUsuario(String correo);
}