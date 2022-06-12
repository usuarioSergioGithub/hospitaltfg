package com.eep.hospital.controller.errores;


import com.eep.hospital.exception.AplicacionException;
import com.eep.hospital.service.SesionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.transaction.UnexpectedRollbackException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import org.thymeleaf.exceptions.TemplateInputException;

import javax.persistence.EntityNotFoundException;


@ControllerAdvice// anotacion para el manejo de excepciones
public class ErroresController extends ResponseEntityExceptionHandler {

    // ===================== Manejo de excepciones a nivel global =============================

    @Autowired
    @Qualifier("sesionServiceImpl")
    SesionService sesionService;

    // ExceptionHandler -> tipo de excepcion a manejar
    // ResponseStatus -> codigo de estado que devolvió http
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal0(AplicacionException error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(AplicacionException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal(AplicacionException error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(IllegalArgumentException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal3(Exception error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(NullPointerException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal4(Exception error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(TemplateInputException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal5(Exception error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(EntityNotFoundException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal6(Exception error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }

    @ExceptionHandler(UnexpectedRollbackException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView errorGlobal7(Exception error){

        ModelAndView mav = new ModelAndView("index");
        mav.addObject("usuarioWeb", sesionService.obtenerCorreoUsuario(true));
        return mav;

    }


}
