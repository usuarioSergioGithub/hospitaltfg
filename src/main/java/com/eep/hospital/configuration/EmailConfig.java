package com.eep.hospital.configuration;

import com.eep.hospital.repositorio.CentroJpaRepository;
import org.jasypt.encryption.StringEncryptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import java.util.Properties;

@Configuration
public class EmailConfig {

    // =============== CLASES ===============

    // --- Clase para el cifrado/descifrado ---
    @Autowired
    StringEncryptor stringEncryptor;

    // =============== REPOSITORIOS ===============

    // --- Repositorio para datos del centro
    @Autowired
    @Qualifier("centroJpaRepository")
    CentroJpaRepository centroJpaRepository;

    // =============== Config. para Java Mail Sender ===============
    @Bean
    public JavaMailSender configuracion() {

        // Crear nueva instancia del servicio para el correo
        JavaMailSenderImpl servidorEmail = new JavaMailSenderImpl();
        servidorEmail.setHost("smtp.gmail.com");// servidor
        servidorEmail.setPort(587);// puerto

        // Usuario y contraseña
        String correo = stringEncryptor.decrypt("H1eIzR5ntDzuiH/bpaqr4EIpXbk7rn9wzt9GcLrSXubEiXt169OnvrF3nTtBKt1U");
        String passwd = stringEncryptor.decrypt("qHq6whTOiBZ6akSyLi/rA1OQdKryZwvKHASctKlC5ps=");


        servidorEmail.setUsername(correo);
        servidorEmail.setPassword(passwd);

        Properties props = servidorEmail.getJavaMailProperties();//Obtener las propiedades que existen para añadir mas
        props.put("mail.transport.protocol", "smtp");// protocolo que se utilizará
        props.put("mail.smtp.auth", "true");// Establecer que se necesita autentificacion
        props.put("mail.smtp.starttls.enable", "true");// Si o si la conexion tiene que ir con TLS (seguridad para la capa que envia los datos)
        props.put("mail.debug", "true");// Mostrar lo que hace en consola

        return servidorEmail;
    }

}
