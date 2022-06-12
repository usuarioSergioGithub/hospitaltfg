package com.eep.hospital.component;

import com.eep.hospital.entity.ClienteEntity;
import com.eep.hospital.entity.Personal;

public class ConversorPersonalPaciente {

    // Este conversor se utiliza debido a que al pedir una cita el paciente puede ser un empleado
    public Personal conversorClienteAPersonal(ClienteEntity paciente){

        Object objeto = paciente;

        return (Personal) objeto;

    }

}
