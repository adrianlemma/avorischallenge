package com.avoris.challenge.exception;

public class EstudianteException extends RuntimeException {

    public EstudianteException(String campo, String valor) {
        super("El valor [" + valor + "] para el campo [" + campo + "], es invalido");
    }
}
