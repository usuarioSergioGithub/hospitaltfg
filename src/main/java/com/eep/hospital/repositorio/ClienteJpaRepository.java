package com.eep.hospital.repositorio;

import com.eep.hospital.entity.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository ("clienteJpaRepository")
public interface ClienteJpaRepository extends JpaRepository<ClienteEntity, Serializable> {

    // Aqui no es el response ya que lo que devuelve de la base de datos es un entity,
    // posteriormente lo onvertire a response
    ClienteEntity findByDni(String dni);

    ClienteEntity findByCorreo(String correo);

    @Transactional
    List<ClienteEntity> findByDniIn(List<String> dniPacientes);

    @Transactional
    List<ClienteEntity> deleteByDniIn(List<String> dniPacientes);

}
