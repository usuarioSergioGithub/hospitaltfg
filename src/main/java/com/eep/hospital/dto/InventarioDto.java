package com.eep.hospital.dto;

import com.eep.hospital.entity.EspecialidadEntity;
import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioDto {
    private Long idArticulo;

    private String tipoArticulo;

    private String modelo;

    private String descripcion;

    private String fabricante;

    @DateTimeFormat(pattern = "yyyy-MM-dd") // formatear el texto obtenido a formato fecha
    private LocalDate fechaCompra;

    private double precioArticulo;

    private int unidades;

    private List<Personal> personal;

    private EspecialidadEntity especialidad;
}

