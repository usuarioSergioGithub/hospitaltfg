package com.eep.hospital.service.impl;

import com.eep.hospital.entity.EspecialidadEntity;
import com.eep.hospital.repositorio.EspecialidadJpaRepository;
import com.eep.hospital.repositorio.PrestacionJpaRepository;
import com.eep.hospital.service.EspecialidadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import java.util.List;

public class EspecialidadServiceImpl implements EspecialidadService {

    @Autowired
    @Qualifier("especialidadJpaRepository")
    EspecialidadJpaRepository especialidadJpaRepository;

    @Autowired
    @Qualifier("prestacionJpaRepository")
    PrestacionJpaRepository prestacionJpaRepository;

    @Override
    public List<EspecialidadEntity> obtenerTodasLasEspecialidades() {
        return null;
    }
}
