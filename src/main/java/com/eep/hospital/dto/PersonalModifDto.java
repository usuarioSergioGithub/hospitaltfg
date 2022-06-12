package com.eep.hospital.dto;

import com.eep.hospital.entity.PerfilUsuario;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Component("personalModifDto")
public class PersonalModifDto {

    @NotBlank
    private String dniNColegiado;

    @NotBlank (message = "Ha leido el mensaje")
    private String nombre;

    @NotBlank
    @Email
    private String correo;

    @NotBlank
    private String apellidos;

    @NotBlank
    private String direccion;

    @NotBlank
    private String localidad;

    @NotBlank
    private String provincia;

    @NotBlank
    @Pattern(regexp = "\\+34?[0-9]{9}")
    private String movil;

    private PerfilUsuario perfilUsuario;

}