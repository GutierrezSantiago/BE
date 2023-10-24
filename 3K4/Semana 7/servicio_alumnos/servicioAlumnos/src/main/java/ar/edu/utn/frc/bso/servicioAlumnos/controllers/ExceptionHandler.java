package ar.edu.utn.frc.bso.servicioAlumnos.controllers;

import ar.edu.utn.frc.bso.servicioAlumnos.exceptions.InvalidDataException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ExceptionHandler extends ResponseEntityExceptionHandler {

    @org.springframework.web.bind.annotation.ExceptionHandler(value = InvalidDataException.class)
    protected ResponseEntity handleInvalidData(RuntimeException ex, WebRequest request) {
        return handleExceptionInternal(ex, "Invalid data", new HttpHeaders(), HttpStatusCode.valueOf(400), request);

    }


}
