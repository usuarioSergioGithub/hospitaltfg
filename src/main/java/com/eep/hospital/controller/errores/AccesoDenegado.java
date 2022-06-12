package com.eep.hospital.controller.errores;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

// --> AccessDeniedHandler: clase que da spring security para manejar los errores de acceso denegado
public class AccesoDenegado implements AccessDeniedHandler {

    // Devolver al usuario a index si accede a una url no autorizada
    @Override
    public void handle(// Aquí hay que tener los 3 parametros ya que es un metodo sobreescrito
            HttpServletRequest request,
            HttpServletResponse response, AccessDeniedException accessDeniedException)
            throws IOException {

        response.sendRedirect("/");

    }
}
