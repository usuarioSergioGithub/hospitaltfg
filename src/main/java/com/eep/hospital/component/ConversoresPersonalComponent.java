package com.eep.hospital.component;

import com.eep.hospital.dto.PersonalDto;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.PersonalHorarioResponse;
import com.eep.hospital.response.PersonalResponse;
import com.eep.hospital.response.PersonalResponseCompleto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresPersonalComponent")
@NoArgsConstructor
public class ConversoresPersonalComponent {

    // Conversor de entity a response
    public List<PersonalResponse> convertirPersonalARespuestas(List<Personal> personal) {

        return personal.stream().map(empleado -> new ModelMapper().map(empleado, PersonalResponse.class)).collect(Collectors.toList());
    }

    public List<PersonalHorarioResponse> convertirPersonalHorarioARespuestas(List<Personal> personal) {

        return personal.stream().map(empleado -> new ModelMapper().map(empleado, PersonalHorarioResponse.class)).collect(Collectors.toList());
    }

    public List<PersonalHorarioResponse> convPersonalComplARespuestas(List<PersonalResponseCompleto> personal) {

        return personal.stream().map(empleado -> new ModelMapper().map(empleado, PersonalHorarioResponse.class)).collect(Collectors.toList());
    }

    public PersonalResponse convertirPersonalDtoARespuesta(PersonalDto personalDto) {

        return new ModelMapper().map(personalDto, PersonalResponse.class);
    }

    public Personal convertirPersonalDtoAEntity(PersonalDto personalDto) {

        return new ModelMapper().map(personalDto, Personal.class);
    }

    public PersonalResponse convertirPersonalARespuesta(Personal personal) {

        return new ModelMapper().map(personal, PersonalResponse.class);
    }
    public PersonalResponseCompleto convertirPersonalCompARespuesta(Personal personal) {

        return new ModelMapper().map(personal, PersonalResponseCompleto.class);
    }

    // Response a entity
    public Personal convertirPersonalCompARespuesta(PersonalResponseCompleto empleado) {

        return new ModelMapper().map(empleado, Personal.class);
    }

    public List<PersonalResponseCompleto> convertirPersonalCompARespuestas(List<Personal> personal) {

        return personal
                .stream()
                .map(empleado -> new ModelMapper().map(empleado, PersonalResponseCompleto.class))
                .collect(Collectors.toList()) ;
    }
}
