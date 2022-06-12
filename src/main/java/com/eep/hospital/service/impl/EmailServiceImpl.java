package com.eep.hospital.service.impl;

import com.eep.hospital.component.ConvMensajesComponent;
import com.eep.hospital.dto.MensajesDto;
import com.eep.hospital.entity.MensajesEntity;
import com.eep.hospital.repositorio.MensajesJpaRepository;
import com.eep.hospital.service.EmailService;
import com.eep.hospital.service.ExcepcionesService;
import com.eep.hospital.service.MensajesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.HashMap;

@Service("emailServiceImpl")
public class EmailServiceImpl implements EmailService {

    @Autowired
    private JavaMailSender email;

    @Autowired
    @Qualifier("mensajesJpaRepository")
    MensajesJpaRepository mensajesJpaRepository;

    @Autowired
    @Qualifier("convMensajesComponent")
    ConvMensajesComponent convMensajesComponent;

    @Autowired
    @Qualifier("mensajesServiceImpl")
    MensajesService mensajesService;

    @Autowired
    @Qualifier("excepcionesServiceImpl")
    ExcepcionesService excepcionesService;

    @Override
    public boolean enviarEmail(MensajesDto mensajesDto) {

        MensajesEntity mensaje = convMensajesComponent.convMensajesDtoAEntity(mensajesDto);

        mensaje.setFehaMensaje(LocalDate.now());

        try {
            mensajesJpaRepository.save(mensaje);

            SimpleMailMessage correo = new SimpleMailMessage();

            correo.setTo(mensajesDto.getCorreo());
            correo.setSubject("Respuesta: " + mensajesDto.getAsunto());
            correo.setText("Hemos recibido tu correo y nos pondremos en contacto con usted lo antes posible.");

            email.send(correo);

            mensajesService.setMensaje("Gracias por ponerse en contacto con nuestro equipo!");

            return true;
        }catch (RuntimeException e){
            HashMap<String, String> error = new HashMap<>();
            error.put("email",
                    "Ocurrió un error");
            excepcionesService.setMensajeError(error);
            return false;
        }


    }
}
