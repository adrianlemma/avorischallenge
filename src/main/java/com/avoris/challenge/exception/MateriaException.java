package com.avoris.challenge.exception;

public class MateriaException extends RuntimeException {

    public MateriaException(String campo, String valor) {
        super("Valor [" + valor + "] invalido para el campo [" + campo + "]");
    }

}
