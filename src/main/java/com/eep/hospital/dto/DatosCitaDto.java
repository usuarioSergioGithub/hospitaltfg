package com.eep.hospital.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
public class DatosCitaDto {

    private LocalTime hora;

    private String fechaDisponible;

    private String dniNColegiado;

    private String dniCliente;
}
