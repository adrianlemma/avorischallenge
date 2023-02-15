package com.avoris.challenge.model;

import com.avoris.challenge.exception.FechaFinalizacionException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.avoris.challenge.constant.Constant.*;
import static org.junit.Assert.assertThrows;

class FechaTest {

    @Test
    @DisplayName("Cuando el constructor recibe año nulo o fuera de rango")
    void cuandoElConstructorRecibeAnioNuloOFueraDeRango() {
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, MES_OK, null));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, MES_OK, -1));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, MES_OK, 5000));
    }

    @Test
    @DisplayName("Cuando el constructor recibe mes nulo o fuera de rango")
    void cuandoElConstructorRecibeMesNuloOFueraDeRango() {
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, null, ANIO_OK));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, -1, ANIO_OK));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(DIA_OK, 13, ANIO_OK));
    }

    @Test
    @DisplayName("Cuando el constructor recibe dia nulo o fuera de rango")
    void cuandoElConstructorRecibeDiaNuloOFueraDeRango() {
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(null, MES_OK, ANIO_OK));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(-1, MES_OK, ANIO_OK));
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(32, MES_OK, ANIO_OK));
        // mes de 30 dias
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(31, 4, ANIO_OK));
        // mes de 31 dias
        Fecha fecha = new Fecha(31, 5, ANIO_OK);
        Assertions.assertFalse(fecha.getFecha().isBlank());
    }

    @Test
    @DisplayName("Validacion de dias en años viciestos")
    void validacionDeDiasEnAnioViciestos() {
        assertThrows(FechaFinalizacionException.class, () ->
                new Fecha(29, 2, 2021));
        Fecha fecha = new Fecha(29, 2, 2020);
        Assertions.assertFalse(fecha.getFecha().isBlank());
    }
}
