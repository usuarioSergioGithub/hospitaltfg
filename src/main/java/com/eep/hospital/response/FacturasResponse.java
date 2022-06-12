package com.eep.hospital.response;

import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Intervenciones;
import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FacturasResponse {

    private LocalDateTime fecha;

    private boolean pagada;

    private ClienteEntity cliente;

    private Personal personal;

    private Intervenciones intervenciones;
}
