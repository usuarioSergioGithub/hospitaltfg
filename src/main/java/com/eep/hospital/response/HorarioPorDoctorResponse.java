package com.eep.hospital.response;

import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioPorDoctorResponse {

    private Personal personal;

    private List<LocalTime> hora;
}
