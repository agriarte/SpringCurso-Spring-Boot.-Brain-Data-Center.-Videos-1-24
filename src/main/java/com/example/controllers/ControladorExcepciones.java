package com.example.controllers;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
// Imports para Spring Framework
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;

// Imports para manejo de excepciones de base de datos
import java.sql.SQLException;
import org.springframework.dao.DataAccessException;

@ControllerAdvice
public class ControladorExcepciones {

    private static final Logger logger = LoggerFactory.getLogger(ControladorExcepciones.class);

    @ExceptionHandler({SQLException.class, DataAccessException.class})
    public ModelAndView handleDatabaseConnectionException(Exception ex) {
        logger.error("Excepción de base de datos capturada: {}", ex.getMessage());

        ModelAndView modelAndView = new ModelAndView("/errores/error2");// nombre de la vista "error.html"
        modelAndView.addObject("errorMessage", "Error al conectar con la base de datos. Por favor, inténtalo de nuevo más tarde.");
        return modelAndView;
    }

  
}
