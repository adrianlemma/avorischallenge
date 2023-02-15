package com.avoris.challenge.controller;

import com.avoris.challenge.exception.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(value = FechaFinalizacionException.class)
    public ResponseEntity<Object> fechaFinalizacionExceptionHandler(FechaFinalizacionException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ValidationException.class)
    public ResponseEntity<Object> validationExceptionHandler(ValidationException ex) {
        return new ResponseEntity<>(ex.getFieldName() + ": " + ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = MateriaException.class)
    public ResponseEntity<Object> materiaExceptionHandler(MateriaException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = EstudianteException.class)
    public ResponseEntity<Object> estudianteExceptionHandler(EstudianteException ex) {
        return new ResponseEntity<>(ex.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = ApiException.class)
    public ResponseEntity<Object> apiExceptionHandler(ApiException ex) {
        return new ResponseEntity<>(ex.getMessage(), ex.getStatus());
    }

}
