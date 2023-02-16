package com.avoris.challenge.mocks;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import com.avoris.challenge.component.EstudianteRequestToEstudiante;
import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.model.Fecha;
import com.avoris.challenge.model.Materia;
import com.avoris.challenge.model.request.EstudianteRequest;
import com.avoris.challenge.model.request.FechaRequest;
import com.avoris.challenge.model.request.MateriaRequest;
import com.avoris.challenge.repository.EstudianteRepository;
import com.avoris.challenge.repository.FechaRepository;
import com.avoris.challenge.repository.MateriaRepository;
import com.avoris.challenge.service.EstudianteService;
import org.assertj.core.util.Lists;

import static com.avoris.challenge.constant.Constant.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

public class Mock {

    // Repository mocks

    public static void mockFechaRepositorySave(FechaRepository repository, Fecha result) {
        when(repository.save(any())).thenReturn(result);
    }

    public static void mockMateriaRepositorySave(MateriaRepository materiaRepository, Materia result) {
        when(materiaRepository.save(any())).thenReturn(result);
    }

    public static void mockEstudianteRepositorySave(EstudianteRepository estudianteRepository, Estudiante result) {
        when(estudianteRepository.save(any())).thenReturn(result);
    }

    public static void mockEstudianteRepositoryFindById(EstudianteRepository estudianteRepository, Estudiante result) {
        when(estudianteRepository.findById(anyInt())).thenReturn(Optional.of(result));
    }

    public static void mockEstudianteRepositoryFindAll(EstudianteRepository estudianteRepository, List<Estudiante> result) {
        when(estudianteRepository.findAll()).thenReturn(result);
    }

    public static void mockEstudianteServiceSave(EstudianteService estudianteService, Estudiante result) {
        when(estudianteService.save(any())).thenReturn(result);
    }

    public static void mockEstudianteServiceFindAll(EstudianteService estudianteService, List<Estudiante> result) {
        when(estudianteService.findAll()).thenReturn(result);
    }

    public static void mockEstudianteServiceFindById(EstudianteService estudianteService, Estudiante result) {
        when(estudianteService.findById(anyInt())).thenReturn(result == null ? Optional.empty() : Optional.of(result));
    }

    public static void mockEstudianteRequestToEstudiante(EstudianteRequestToEstudiante converter, Estudiante result) {
        when(converter.execute(any())).thenReturn(result);
    }

    public static void mockEstudianteRequestToEstudianteThrowsException(EstudianteRequestToEstudiante converter, RuntimeException ex) {
        when(converter.execute(any())).thenThrow(ex);
    }

    //DTO mocks

    public static FechaRequest mockFechaRequest() {
        return new FechaRequest(DIA_OK, MES_OK, ANIO_OK);
    }

    public static MateriaRequest mockMateriaRequest() {
        return new MateriaRequest(NOMBRE_MATERIA_OK, CALIFICACION_OK);
    }

    public static EstudianteRequest mockEstudianteRequest() {
        return new EstudianteRequest(NOMBRE_ESTUDIANTE_OK, EDAD_OK, mockFechaRequest(), Lists.newArrayList(mockMateriaRequest()));
    }

    public static Fecha mockFecha() {
        return new Fecha(DIA_OK, MES_OK, ANIO_OK);
    }

    public static Materia mockMateria() {
        return new Materia(NOMBRE_MATERIA_OK, CALIFICACION_OK, null);
    }

    public static Estudiante mockEstudiante() {
        List<Materia> materias = new ArrayList<>();
        materias.add(mockMateria());
        return new Estudiante(NOMBRE_ESTUDIANTE_OK, EDAD_OK, mockFecha(), materias);
    }

}
