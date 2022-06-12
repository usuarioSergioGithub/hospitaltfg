package com.eep.hospital.service;


import com.eep.hospital.dto.InventarioDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;

public interface ModificacionService {

    boolean modificarInventario(InventarioDto inventario);

    boolean modificarEmpleado(Personal empleado);

    boolean modificarCliente(ClienteEntity cliente);
}
