package com.eep.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("datosPlantillaDto")
public class DatosPlantillaDto {

    private String titulo;
    private String nombreBoton;
}
