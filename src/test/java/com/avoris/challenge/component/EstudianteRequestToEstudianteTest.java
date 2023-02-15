package com.avoris.challenge.component;

import com.avoris.challenge.enumerator.Mes;
import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.model.request.EstudianteRequest;
import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static com.avoris.challenge.mocks.Mock.mockEstudianteRequest;
import static org.junit.jupiter.api.Assertions.assertEquals;

class EstudianteRequestToEstudianteTest {

    EstudianteRequestToEstudiante estudianteRequestToEstudiante;

    @BeforeEach
    void setup() {
        estudianteRequestToEstudiante = new EstudianteRequestToEstudiante();
    }

    @Test
    @DisplayName("Conversion de EstudianteRequest a Estudiante")
    void conversionDeEstudianteRequestAEstudiante() {
        EstudianteRequest estudianteRequest = mockEstudianteRequest();
        Estudiante estudiante = estudianteRequestToEstudiante.execute(estudianteRequest);

        assertEquals(estudianteRequest.getNombre(), estudiante.getNombre());
        assertEquals(estudianteRequest.getEdad(), estudiante.getEdad());
        assertEquals(estudianteRequest.getFechaFinalizacion().getDia() + " de " +
                Mes.values()[estudianteRequest.getFechaFinalizacion().getMes() - 1].getNombre() + " de " +
                estudianteRequest.getFechaFinalizacion().getAnio(), estudiante.getFechaFinalizacion().getFecha());
        assertEquals(estudianteRequest.getMateriasCursadas().get(0).getNombre(),
                estudiante.getMateriasCursadas().get(0).getNombre());
        assertEquals(estudianteRequest.getMateriasCursadas().get(0).getCalificacion(),
                estudiante.getMateriasCursadas().get(0).getCalificacion());
    }
}
