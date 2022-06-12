package com.eep.hospital.service;

import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.entity.ClienteEntity;

public interface ClienteService {

    ClienteEntity guardar(ClienteDto clienteDto);
}
