package com.eep.hospital.component;

import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.HorarioResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresHorarioComponent")
@NoArgsConstructor
public class ConversoresHorarioComponent {

    // Conversor de entity a response
    public List<HorarioResponse> convertirHorariosARespuestas(List<Personal> horarios) {

        return horarios.stream().map(horario -> new ModelMapper().map(horario, HorarioResponse.class)).collect(Collectors.toList()) ;
    }

}
