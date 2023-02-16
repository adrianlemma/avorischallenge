package com.avoris.challenge.model;

import java.util.*;
import java.util.stream.Stream;
import org.assertj.core.util.Sets;
import org.junit.jupiter.api.*;

import static com.avoris.challenge.constant.Constant.*;
import static com.avoris.challenge.mocks.Mock.mockFecha;
import static com.avoris.challenge.mocks.Mock.mockMateria;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.DynamicContainer.dynamicContainer;
import static org.junit.jupiter.api.DynamicTest.dynamicTest;

class EstudianteTest {

    Fecha fecha;

    Materia materia;

    @BeforeEach
    void init() {
        fecha = new Fecha(DIA_OK, MES_OK, ANIO_OK);
        materia = new Materia(NOMBRE_MATERIA_OK, CALIFICACION_OK, null);
    }

    @TestFactory
    @DisplayName("Test validacion de parametros no nulos")
    Stream<DynamicNode> dynamicTestNullParams() {
        return listOfNullParams().stream().map(input -> dynamicContainer("test null validation",
                Stream.of(dynamicTest("not null", () -> generateTest(input)))));
    }

    private List<Set<String>> listOfNullParams() {
        return Arrays.asList(
                Sets.newHashSet(Arrays.asList("edad", "fecha", "materias")),
                Sets.newHashSet(Arrays.asList("nombre", "fecha", "materias")),
                Sets.newHashSet(Arrays.asList("nombre", "edad", "materias")),
                Sets.newHashSet(Arrays.asList("nombre", "edad", "fecha")));
    }

    private RuntimeException generateTest(Set<String> input) {
        return assertThrows(RuntimeException.class, () ->
                new Estudiante(
                        input.contains("nombre") ? NOMBRE_ESTUDIANTE_OK : null,
                        input.contains("edad") ? EDAD_OK : null,
                        input.contains("fecha") ? mockFecha() : null,
                        input.contains("materias") ? Arrays.asList(mockMateria()) : null
                ));
    }
}
