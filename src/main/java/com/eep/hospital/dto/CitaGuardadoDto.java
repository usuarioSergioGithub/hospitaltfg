package com.eep.hospital.dto;

import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CitaGuardadoDto {

    private LocalTime hora;

    private LocalDate fechaDisponible;

    private ClienteEntity clientePaciente ;

    private Personal empleadoPaciente ;

    private Personal empleadoDoctor;


}
