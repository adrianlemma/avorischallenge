package com.avoris.challenge.model.request;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class MateriaRequest {

    @NotNull(message = "El nombre de la materia es un campo obligatorio")
    @NotBlank(message = "El nombre de la materia debe contener caracteres validos")
    private String nombre;

    @NotNull(message = "La calificacion de la materia es un campo obligatorio")
    @DecimalMin(value = "0.0", message = "La calificacion no puede ser negativa")
    private Double calificacion;

    public String getNombre() {
        return nombre;
    }

    public Double getCalificacion() {
        return calificacion;
    }

    public MateriaRequest() { }

    public MateriaRequest(String nombre, Double calificacion) {
        this.nombre = nombre;
        this.calificacion = calificacion;
    }

}
