package com.avoris.challenge.model.request;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FechaRequest {

    @NotNull(message = "El dia de finalizacion es un campo obligatorio")
    @Min(value = 0, message = "El dia no puede ser negativo")
    private Integer dia;

    @NotNull(message = "El mes de finalizacion es un campo obligatorio")
    @Min(value = 0, message = "El mes no puede ser negativo")
    private Integer mes;

    @NotNull(message = "El año de finalizacion es un campo obligatorio")
    @Min(value = 0, message = "El año no puede ser negativo")
    private Integer anio;

    public Integer getDia() {
        return dia;
    }

    public Integer getMes() {
        return mes;
    }

    public Integer getAnio() {
        return anio;
    }

    public FechaRequest() { }

    public FechaRequest(Integer dia, Integer mes, Integer anio) {
        this.dia = dia;
        this.mes = mes;
        this.anio = anio;
    }

}
