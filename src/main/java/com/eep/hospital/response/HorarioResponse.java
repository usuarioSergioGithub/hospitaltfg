package com.eep.hospital.response;

import com.eep.hospital.entity.Personal;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalTime;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class HorarioResponse {

    private LocalTime hora;

    private Set<Personal> personal;
}
