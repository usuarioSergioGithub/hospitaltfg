package com.eep.hospital.service;

import com.eep.hospital.dto.MensajesDto;

public interface EmailService {

    boolean enviarEmail(MensajesDto mensajesDto);
}
