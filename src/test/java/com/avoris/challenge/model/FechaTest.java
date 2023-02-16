package com.avoris.challenge.model;

import com.avoris.challenge.exception.FechaFinalizacionException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Stream;
import org.junit.jupiter.api.*;

import static com.avoris.challenge.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class FechaTest {

    @TestFactory
    @DisplayName("Test validacion de parametros no nulos")
    Stream<DynamicNode> dynamicTestNullParams() {
        return listOfParams().stream().map(input -> dynamicContainer("test " + input + " validation",
                Stream.of(
                        dynamicTest("Año " + input, () -> testData(DIA_OK, MES_OK, null, input)),
                        dynamicTest("Mes " + input, () -> testData(DIA_OK, null, ANIO_OK, input)),
                        dynamicTest("Mes " + input, () -> testData(null, MES_OK, ANIO_OK, input)))));
    }

    private List<String> listOfParams() {
        return Arrays.asList("Null", "Negative", "Exceeded");
    }

    private Object testData(Integer dia, Integer mes, Integer anio, String tipo) {
        if (tipo.equals("Null")) {
            return assertThrows(FechaFinalizacionException.class,
                    () -> new Fecha(dia, mes, anio));
        }
        if (tipo.equals("Negative")) {
            return assertThrows(FechaFinalizacionException.class,
                    () -> new Fecha(dia == null ? -1 : dia, mes == null ? -1 : mes, anio == null ? -1 : anio));
        }
        if (tipo.equals("Exceeded")) {
            return assertThrows(FechaFinalizacionException.class,
                    () -> new Fecha(dia == null ? 32 : dia, mes == null ? 13 : mes, anio == null ? 4000 : anio));
        }
        return null;
    }

    @Nested
    @DisplayName("Test dias segun el mes y año viciesto")
    class testDias {
        @Test
        @DisplayName("Cuando el dia esta fuera de rango segun su mes")
        void cuandoElConstructorRecibeDiaNuloOFueraDeRango() {
            // mes de 30 dias
            assertThrows(FechaFinalizacionException.class, () ->
                    new Fecha(31, 4, ANIO_OK));
            // mes de 31 dias
            assertThrows(FechaFinalizacionException.class, () ->
                    new Fecha(32, 4, ANIO_OK));
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
}
