package com.eep.hospital.component;

import com.eep.hospital.entity.Facturas;
import com.eep.hospital.response.FacturasResponse;
import lombok.NoArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component("conversoresFacturasComponent")
@NoArgsConstructor
public class ConversoresFacturasComponent {

    // Conversor de entity a response
    public List<FacturasResponse> convertirFacturaARespuestas(List<Facturas> facturas) {

        return facturas.stream().map(factura -> new ModelMapper().map(factura, FacturasResponse.class)).collect(Collectors.toList());
    }
    public FacturasResponse convertirFacturaARespuesta(Facturas factura) {

        return new ModelMapper().map(factura, FacturasResponse.class);
    }
}
