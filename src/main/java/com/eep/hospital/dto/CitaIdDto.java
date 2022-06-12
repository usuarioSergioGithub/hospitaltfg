package com.eep.hospital.dto;

import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("citaIdDto")
public class CitaIdDto {

    private Long id;

    private String dniNColegiado;

    private LocalTime hora;


}
