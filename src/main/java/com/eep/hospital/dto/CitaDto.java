package com.eep.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Component("citaDto")
public class CitaDto {

    private LocalDateTime fecha;

    private PersonalDto personal;

    private EspecialidadDto especialidades;

    private PrestacionesDto prestaciones;

    @NotBlank
    @Email
    private String correoIntroducido;

    private boolean paraMi;

    private boolean usuarioEstandar;

}
