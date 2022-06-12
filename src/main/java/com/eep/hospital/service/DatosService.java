package com.eep.hospital.service;

import com.eep.hospital.dto.ClienteModifDto;
import com.eep.hospital.dto.PersonalModifDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.ClienteResponseCompleto;
import com.eep.hospital.response.PersonalResponseCompleto;

import java.util.List;

public interface DatosService {

    Personal completarDatos(PersonalModifDto personalModifDto, Personal empleado);

    Personal asignarDatos(PersonalModifDto datoJusto, List<PersonalResponseCompleto> datosCompletos);

    ClienteEntity asignarDatosCliente(ClienteModifDto datoJusto, List<ClienteResponseCompleto> datosCompletos);
}
