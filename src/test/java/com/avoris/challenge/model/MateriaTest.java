package com.avoris.challenge.model;

import java.util.stream.Stream;
import com.avoris.challenge.exception.MateriaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.DynamicNode;
import org.junit.jupiter.api.TestFactory;

import static com.avoris.challenge.constant.Constant.*;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class MateriaTest {

    @TestFactory
    @DisplayName("Test validacion de parametros no nulos")
    Stream<DynamicNode> dynamicTestNullParams() {
        return Stream.of("Null", "Invalid").map(input ->
                dynamicContainer("test Materia validation", Stream.of(
                        dynamicTest("Nombre " + input, () -> testData(NOMBRE_ESTUDIANTE_OK, null, input)),
                        dynamicTest("Calificacion " + input, () -> testData( null, CALIFICACION_OK, input)))));
    }

    private Object testData(String nombre, Double calificacion, String tipo) {
        if (tipo.equals("Null")) {
            return assertThrows(MateriaException.class,
                    () -> new Materia(nombre, calificacion, null));
        }
        if (tipo.equals("Invalid")) {
            return assertThrows(MateriaException.class,
                    () -> new Materia(nombre == null ? NOMBRE_MATERIA_ERROR : nombre,
                            calificacion == null ? CALIFICACION_ERROR : calificacion, null));
        }
        return null;
    }
}
