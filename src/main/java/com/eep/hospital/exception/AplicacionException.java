package com.eep.hospital.exception;

public class AplicacionException extends Exception {

    // Creacion de mensajes personalizados cuando se generen excepciones a nivel global
    public AplicacionException(String mensaje) {
        super(mensaje);
    }
}
