package com.eep.hospital.component;

import com.eep.hospital.dto.PrestacionesDto;
import com.eep.hospital.entity.PrestacionesEntity;
import com.eep.hospital.response.PrestacionesResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresPrestacionComponent")
@NoArgsConstructor
public class ConversoresPrestacionComponent {

    // Conversor de entity a response
    public List<PrestacionesResponse> convertirPrestacionARespuestas(List<PrestacionesEntity> prestaciones) {

        return prestaciones.stream().map(prestacion -> new ModelMapper().map(prestacion, PrestacionesResponse.class)).collect(Collectors.toList());
    }

    public PrestacionesResponse prestacionDtoAResponse(PrestacionesDto prestacionDto) {

        return new ModelMapper().map(prestacionDto, PrestacionesResponse.class);
    }

}
