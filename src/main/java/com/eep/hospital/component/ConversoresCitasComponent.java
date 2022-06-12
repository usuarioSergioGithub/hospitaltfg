package com.eep.hospital.component;

import com.eep.hospital.dto.CitaGuardadoDto;
import com.eep.hospital.entity.Cita;
import com.eep.hospital.response.CitaResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresCitasComponent")
@NoArgsConstructor
public class ConversoresCitasComponent {

    // Conversor de entity a response
    public List<CitaResponse> convertirCitaARespuestas(List<Cita> citas) {

        return citas.stream().map(cita -> new ModelMapper().map(cita, CitaResponse.class)).collect(Collectors.toList());
    }

    // Conversor de entity a response
    public CitaResponse convertirCitaARespuesta(Cita cita) {

        return new ModelMapper().map(cita, CitaResponse.class);
    }

    // citaGuardadoDto a entity
    public Cita convertirCitaGuardadoAEntity(CitaGuardadoDto citaGuardadoDto) {

        return new ModelMapper().map(citaGuardadoDto, Cita.class);
    }


}
