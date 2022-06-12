package com.eep.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Component("mensajesDto")
public class MensajesDto {

    private LocalDate fehaMensaje;

    @Email
    private String correo;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellidos;

    private String asunto;//select

    @Size(min = 0, max = 500)
    private String mensaje;


}
