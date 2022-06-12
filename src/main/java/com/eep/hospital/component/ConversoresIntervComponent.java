package com.eep.hospital.component;

import com.eep.hospital.entity.ClienteIntervDoctor;
import com.eep.hospital.response.IntervencionesResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresIntervComponent")
@NoArgsConstructor
public class ConversoresIntervComponent {

    // Conversor de entity a response
    public List<IntervencionesResponse> convertirIntervencionARespuestas(List<ClienteIntervDoctor> intervenciones) {

        return intervenciones.stream().map(interv -> new ModelMapper().map(interv, IntervencionesResponse.class)).collect(Collectors.toList());
    }

}