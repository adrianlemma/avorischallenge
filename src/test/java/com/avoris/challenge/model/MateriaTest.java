package com.avoris.challenge.model;

import com.avoris.challenge.exception.MateriaException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.avoris.challenge.constant.Constant.*;
import static org.junit.Assert.assertThrows;

class MateriaTest {

    @Test
    @DisplayName("cuando el nombre de materia es nulo o invalido")
    void cuandoElNombreDeMateriaEsNuloOInvalido() {
        assertThrows(MateriaException.class, () ->
                new Materia(null , CALIFICACION_OK, null));
        assertThrows(MateriaException.class, () ->
                new Materia(NOMBRE_MATERIA_ERROR , CALIFICACION_OK, null));
    }

    @Test
    @DisplayName("cuando la calificacion de materia es nula o invalida")
    void cuandoLaCalificacionDeMateriaEsNulaOInvalida() {
        assertThrows(MateriaException.class, () ->
                new Materia(NOMBRE_MATERIA_OK , null, null));
        assertThrows(MateriaException.class, () ->
                new Materia(NOMBRE_MATERIA_ERROR , CALIFICACION_ERROR, null));
    }
}
