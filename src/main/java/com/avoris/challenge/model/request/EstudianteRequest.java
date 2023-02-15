package com.avoris.challenge.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class EstudianteRequest {

    @NotNull(message = "El nombre es un campo obligatorio")
    @NotBlank(message = "El nombre debe contener caracteres validos")
    private String nombre;

    @NotNull(message = "La edad es un campo obligatorio")
    @Min(value = 0, message = "La edad no puede ser negativa")
    private Integer edad;

    @NotNull(message = "Debe ingresar una fecha de finalizacion")
    private FechaRequest fechaFinalizacion;

    @NotNull(message = "Debe ingresar una lista de materias cursadas")
    private List<MateriaRequest> materiasCursadas;

    public String getNombre() {
        return nombre;
    }
    public Integer getEdad() {
        return edad;
    }

    public FechaRequest getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public List<MateriaRequest> getMateriasCursadas() {
        return materiasCursadas;
    }

    public EstudianteRequest() { }

    public EstudianteRequest(String nombre, Integer edad, FechaRequest fechaFinalizacion, List<MateriaRequest> materiasCursadas) {
        this.nombre = nombre;
        this.edad = edad;
        this.fechaFinalizacion = fechaFinalizacion;
        this.materiasCursadas = materiasCursadas;
    }

}
