package com.avoris.challenge.exception;

public class FechaFinalizacionException extends RuntimeException {

    public FechaFinalizacionException(String tipo, Integer valor) {
        super("El " + tipo + " de Finalizacion: " + valor + " es incorrecto");
    }

}
