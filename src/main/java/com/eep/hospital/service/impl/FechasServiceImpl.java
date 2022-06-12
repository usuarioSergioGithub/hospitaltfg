package com.eep.hospital.service.impl;

import com.eep.hospital.dto.PersonalFechaDto;
import com.eep.hospital.entity.Personal;
import com.eep.hospital.response.PersonalResponseCompleto;
import com.eep.hospital.service.FechasService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service("fechasServiceImpl")
public class FechasServiceImpl implements FechasService{

    @Autowired
    @Qualifier("personalFechaDto")
    PersonalFechaDto personalFechaDto;

    @Override
    public List<PersonalResponseCompleto> establecerProximaCita(List<Personal> empleados) {

        List<PersonalResponseCompleto> perResp = new ArrayList<>();

        for (Personal empleado: empleados) {

            PersonalResponseCompleto empleadoResponse =
                    new ModelMapper().map(empleado, PersonalResponseCompleto.class);

            LocalDateTime localDateTime = obtenerProximaFecha(empleado.getDiaDisponible());
            empleadoResponse.setProximaCita(
                    localDateTime
            );

            perResp.add(empleadoResponse);

        }
/*        List<PersonalResponseCompleto> collect = empleados
                .stream()
                .map(empleado -> new ModelMapper()
                        .map(empleado, PersonalResponseCompleto.class)
                ).collect(Collectors.toList());*/

        return perResp;
    }

    @Override
    public LocalDateTime obtenerProximaFecha(int diaDisponible) {

        LocalDateTime fechaActual = LocalDateTime.now();

        /*
        Los dias empeizan por 1
         */
        boolean diaEncontrado=false;
        do{

            if (fechaActual.getDayOfWeek().getValue()==diaDisponible){
                diaEncontrado=true;
            }else{
                fechaActual=fechaActual.plusDays(1);
            }

        }while (!diaEncontrado);

        return fechaActual;
    }

}
