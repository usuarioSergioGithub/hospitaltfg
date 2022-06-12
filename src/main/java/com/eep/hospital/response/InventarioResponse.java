package com.eep.hospital.response;

import com.eep.hospital.entity.EspecialidadEntity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InventarioResponse {

    private Long idArticulo;

    private String tipoArticulo;

    private String modelo;

    private String descripcion;

    private String fabricante;

    private LocalDate fechaCompra;

    private double precioArticulo;

    private int unidades;

    private EspecialidadEntity especialidad;

}