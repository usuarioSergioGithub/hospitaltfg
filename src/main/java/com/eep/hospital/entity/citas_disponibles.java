package com.eep.hospital.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
public class citas_disponibles {

    private LocalTime horas;

    private List<Personal> personals;
}
