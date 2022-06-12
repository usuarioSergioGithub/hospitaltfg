package com.eep.hospital.component;

import com.eep.hospital.entity.EspecialidadEntity;
import com.eep.hospital.response.EspecialidadResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresEspecialidadComponent")
@NoArgsConstructor
public class ConversoresEspecialidadComponent {

    // Conversor de entity a response
    public List<EspecialidadResponse> convertirEspecialidadARespuestas(List<EspecialidadEntity> especialidades) {

        return especialidades.stream().map(especialidad -> new ModelMapper().map(especialidad, EspecialidadResponse.class)).collect(Collectors.toList());
    }

    /*public CitaDto cambiarEspecialidades(CitaDto citaDto, List<PrestacionesResponse> especialidadesTotales) {


        for (int i = 0; i < citaDto.getPrestaciones().size(); i++) {
            citaDto.getPrestaciones().get(i).getEspecialidad().setNombreEspecialidad(especialidadesTotales.get(i).getEspecialidad().getNombreEspecialidad());
        }

        return citaDto;
    }*/



}
