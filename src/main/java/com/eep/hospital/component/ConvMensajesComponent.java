package com.eep.hospital.component;

import com.eep.hospital.dto.MensajesDto;
import com.eep.hospital.entity.MensajesEntity;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

@Component("convMensajesComponent")
public class ConvMensajesComponent {

    public MensajesEntity convMensajesDtoAEntity(MensajesDto mensajesDto){

        return new ModelMapper().map(mensajesDto, MensajesEntity.class);

    }
}
