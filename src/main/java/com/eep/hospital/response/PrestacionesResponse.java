package com.eep.hospital.response;

import com.eep.hospital.entity.EspecialidadEntity;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PrestacionesResponse {

    private String nombrePrestacion;

    private EspecialidadEntity especialidad;


    
}
