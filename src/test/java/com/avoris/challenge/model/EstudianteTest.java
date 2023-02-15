package com.avoris.challenge.model;

import com.avoris.challenge.exception.EstudianteException;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.avoris.challenge.constant.Constant.*;
import static org.junit.Assert.assertThrows;

class EstudianteTest {

    Fecha fecha;

    Materia materia;

    @BeforeEach
    void init() {
        fecha = new Fecha(DIA_OK, MES_OK, ANIO_OK);
        materia = new Materia(NOMBRE_MATERIA_OK, CALIFICACION_OK, null);
    }

    @Test
    @DisplayName("cuando el nombre de estudiante es nulo o invalido")
    void cuandoElNombreDeEstudianteEsNuloOInvalido() {
        assertThrows(EstudianteException.class, () ->
                new Estudiante(null, EDAD_OK, fecha, Lists.newArrayList(materia)));
        assertThrows(EstudianteException.class, () ->
                new Estudiante(NOMBRE_ESTUDIANTE_ERROR, EDAD_OK, fecha, Lists.newArrayList(materia)));
    }

    @Test
    @DisplayName("cuando la edad del estudiante es nula o invalida")
    void cuandoLaEdadDelEstudianteEsNulaOInvalida() {
        assertThrows(EstudianteException.class, () ->
                new Estudiante(NOMBRE_ESTUDIANTE_OK, null, fecha, Lists.newArrayList(materia)));
        assertThrows(EstudianteException.class, () ->
                new Estudiante(NOMBRE_ESTUDIANTE_OK, EDAD_ERROR, fecha, Lists.newArrayList(materia)));
    }

    @Test
    @DisplayName("cuando la fecha de finalizacion del estudiante es nula")
    void cuandoLaFechaDeFinalizacionDelEstudianteEsNula() {
        assertThrows(EstudianteException.class, () ->
                new Estudiante(NOMBRE_ESTUDIANTE_OK, EDAD_OK, null, Lists.newArrayList(materia)));
    }

    @Test
    @DisplayName("cuando las materias cursadas del estudiante son nulas")
    void cuandoLasMateriasCursadasDelEstudianteSonNulas() {
        assertThrows(EstudianteException.class, () ->
                new Estudiante(NOMBRE_ESTUDIANTE_OK, EDAD_OK, fecha, null));
    }
}
