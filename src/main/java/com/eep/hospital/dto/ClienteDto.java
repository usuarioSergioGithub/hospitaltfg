package com.eep.hospital.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import javax.validation.constraints.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Component("clienteDto")
public class ClienteDto {

    @Pattern(regexp = "[0-9]{8}[A-Z]{1}")
    private String dni;

    @NotBlank
    @Size(min = 1, max = 30)
    private String nombre;

    @NotBlank
    @Size(min = 1, max = 40)
    private String apellidos;

    @NotBlank
    @Size(min = 1, max = 40)
    private String direccion;

    @NotBlank
    @Size(min = 1, max = 40)
    private String localidad;

    @NotBlank
    @Size(min = 1, max = 40)
    private String provincia;

    @Pattern(regexp = "[0-9]{5}")
    private String cp;

    //@Pattern(regexp = "[0-9]{9}")
    @Size(max = 9)
    private String telFijo; // -- es opcional, pero el movil es obligatorio

    @Pattern(regexp = "\\+34?[0-9]{9}")
    private String movil;

    @Email
    private String correo;

    @NotBlank
    @Min(value = 4)
    private String password;
}
