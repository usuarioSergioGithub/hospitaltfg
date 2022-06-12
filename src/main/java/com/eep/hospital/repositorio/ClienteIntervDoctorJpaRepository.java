package com.eep.hospital.repositorio;

import com.eep.hospital.entity.ClienteIntervDoctor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@Repository("clienteIntervDoctorJpaRepository")
public interface ClienteIntervDoctorJpaRepository extends JpaRepository<ClienteIntervDoctor, Serializable> {

    List<ClienteIntervDoctor> findByClienteCorreo(String paciente);

    List<ClienteIntervDoctor> findByPersonalCorreo(String doctor);

    @Transactional
    List<ClienteIntervDoctor> deleteByPersonalDniNColegiadoIn(List<String> dniNColegiadoPersonal);
}