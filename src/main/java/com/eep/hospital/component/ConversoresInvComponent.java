package com.eep.hospital.component;

import com.eep.hospital.dto.InventarioDto;
import com.eep.hospital.entity.Inventario;
import com.eep.hospital.response.InventarioResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresInvComponent")
@NoArgsConstructor
public class ConversoresInvComponent {

    // Conversor de entity a response
    public List<InventarioResponse> convertirInvARespuestas(List<Inventario> articulos) {

        return articulos.stream().map(cita -> new ModelMapper().map(cita, InventarioResponse.class)).collect(Collectors.toList());
    }

    // Conversor de entity a response
    public InventarioResponse convertirCitaARespuesta(Inventario inventario) {

        return new ModelMapper().map(inventario, InventarioResponse.class);
    }

    public Inventario convertirInvAEntity(InventarioDto inventario) {

        return new ModelMapper().map(inventario, Inventario.class);
    }
}
