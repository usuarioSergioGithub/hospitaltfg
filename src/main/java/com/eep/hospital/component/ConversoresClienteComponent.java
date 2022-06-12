package com.eep.hospital.component;

import com.eep.hospital.dto.ClienteDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.response.ClienteResponse;
import com.eep.hospital.response.ClienteResponseCompleto;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresClienteComponent")
@NoArgsConstructor
public class ConversoresClienteComponent {

    // Conversor de dto a entity
    public ClienteEntity convertirClienteAEntidad(ClienteDto clienteDto) {

        return new ModelMapper().map(clienteDto, ClienteEntity.class);

    }

    public ClienteResponse convertirClienteARespuesta(ClienteEntity cliente) {

        return new ModelMapper().map(cliente, ClienteResponse.class);
    }

    public ClienteEntity convertirClienteCompARespuesta(ClienteResponseCompleto cliente) {

        return new ModelMapper().map(cliente, ClienteEntity.class);
    }

    public List<ClienteResponseCompleto> convertirClienteCompARespuestas(List<ClienteEntity> clientes) {

        return clientes
                .stream()
                .map(cliente -> new ModelMapper().map(cliente, ClienteResponseCompleto.class))
                .collect(Collectors.toList()) ;
    }

    // Conversor de entity a response
    public List<ClienteResponse> convertirClienteARespuestas(List<ClienteEntity> cliente) {

        return cliente.stream().map(datos -> new ModelMapper().map(datos, ClienteResponse.class)).collect(Collectors.toList());
    }

}
