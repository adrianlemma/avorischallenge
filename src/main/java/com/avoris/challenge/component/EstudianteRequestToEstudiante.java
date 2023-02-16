package com.avoris.challenge.component;

import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.model.Fecha;
import com.avoris.challenge.model.Materia;
import com.avoris.challenge.model.request.EstudianteRequest;
import java.util.ArrayList;
import java.util.List;
import org.springframework.stereotype.Component;

@Component
public class EstudianteRequestToEstudiante {

    public Estudiante execute(EstudianteRequest request) {
        List<Materia> materias = new ArrayList<>();
        request.getMateriasCursadas().forEach(materia ->
                materias.add(new Materia(materia.getNombre(), materia.getCalificacion(), null)));
        return new Estudiante(request.getNombre(), request.getEdad(),
                new Fecha(request.getFechaFinalizacion().getDia(),
                        request.getFechaFinalizacion().getMes(),
                        request.getFechaFinalizacion().getAnio()),
                materias);
    }
}
