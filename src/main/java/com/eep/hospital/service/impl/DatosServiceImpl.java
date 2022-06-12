package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConversoresClienteComponent;
import com.eep.hospital.component.ConversoresPersonalComponent;
import com.eep.hospital.dto.ClienteModifDto;
import com.eep.hospital.dto.PersonalModifDto;
import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.ClienteResponseCompleto;
import com.eep.hospital.response.PersonalResponseCompleto;
import com.eep.hospital.service.DatosService;
import org.modelmapper.Conditions;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("datosServiceImpl")
public class DatosServiceImpl implements DatosService {

    @Autowired
    @Qualifier("conversoresPersonalComponent")
    ConversoresPersonalComponent conversoresPersonalComponent;

    @Autowired
    @Qualifier("conversoresClienteComponent")
    ConversoresClienteComponent conversoresClienteComponent;

    @Override
    public Personal completarDatos(PersonalModifDto empAModificar, Personal empleadoBd) {

        /*
        empAModificar -> datos modificador pero hay que castearlo para obtener los campos
        empAModificarComp -> ya se han añadido los nuevos campos pero no se han rellenado
         */

        Personal modificado = new Personal();

        /*
        Yo tengo Personal         -> el obtenido de la base de datos
        tambien  PersonalModifDto -> el obtenido del formulario

        quiero que me mapee de personalModif a personal
        y luego empleado a personal
         */

        if (!empleadoBd.getDniNColegiado().equals(empAModificar.getDniNColegiado())){

        }

        ModelMapper modelMapper = new ModelMapper();

        Personal empAModificarComp = modelMapper.map(empAModificar, Personal.class);

        modelMapper.getConfiguration().setPropertyCondition(Conditions.isNull());


        modelMapper.map(empleadoBd, empAModificarComp);

        return modificado;

    }

    @Override
    public Personal asignarDatos(PersonalModifDto datoJusto, List<PersonalResponseCompleto> datosCompletos) {
        /*
        Aqui como voy a necesitar despues todos los datos por cada empleado,
        envio los justos para el listado, pero envio tambien los datos restantes para que
        se autorrellenen en los input y al recoger, recogería Personal con todos los datos
        mas los cambiados
        */

        /*
        por cada dato si coincide el dni, es decir, es el mismo empleado, le completo los datos
         */

        for (PersonalResponseCompleto datoCompleto: datosCompletos) {

            if (datoJusto.getDniNColegiado().equals(datoCompleto.getDniNColegiado())){

                new ModelMapper().map(datoJusto, datoCompleto);

                return conversoresPersonalComponent.convertirPersonalCompARespuesta(
                        datoCompleto
                );

            }

        }

        return null;
    }

    @Override
    public ClienteEntity asignarDatosCliente(ClienteModifDto datoJusto, List<ClienteResponseCompleto> datosCompletos) {

        for (ClienteResponseCompleto datoCompleto: datosCompletos) {

            if (datoJusto.getDni().equals(datoCompleto.getDni())){

                new ModelMapper().map(datoJusto, datoCompleto);

                return conversoresClienteComponent.convertirClienteCompARespuesta(
                        datoCompleto
                );

            }

        }

        return null;
    }


}
