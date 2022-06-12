package com.eep.hospital.repositorio;

import com.eep.hospital.entity.MensajesEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository ("mensajesJpaRepository")
public interface MensajesJpaRepository extends JpaRepository<MensajesEntity, Serializable> {



}
