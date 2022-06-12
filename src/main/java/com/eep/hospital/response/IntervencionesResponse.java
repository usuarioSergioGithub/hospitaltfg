package com.eep.hospital.response;


import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Intervenciones;
import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class IntervencionesResponse {

    Personal personal;

    ClienteEntity cliente;

    Intervenciones intervenciones;
}
