package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.repository.EstudianteRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static com.avoris.challenge.mocks.Mock.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class EstudianteServiceImplTest {

    @Mock
    EstudianteRepository estudianteRepository;

    @InjectMocks
    EstudianteServiceImpl estudianteService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Persiste estudiante correctamente")
    void persisteEstudianteCorrectamente() {
        Estudiante estudiante = mockEstudiante();
        mockEstudianteRepositorySave(estudianteRepository, estudiante);
        Estudiante result = estudianteService.save(estudiante);
        assertEquals(estudiante.getNombre(), result.getNombre());
        assertEquals(estudiante.getEdad(), result.getEdad());
        assertEquals(estudiante.getFechaFinalizacion().getFecha(), result.getFechaFinalizacion().getFecha());
        assertEquals(estudiante.getMateriasCursadas().get(0).getNombre(), result.getMateriasCursadas().get(0).getNombre());
        verify(estudianteRepository).save(any());
    }

    @Test
    @DisplayName("Al intentar persistir null")
    void alIntentarPersistirNull() {
        Estudiante result = estudianteService.save(null);
        assertNull(result);
        verify(estudianteRepository, times(0)).save(any());
    }

    @Test
    @DisplayName("Obtiene estudiante por id correctamente")
    void ObtieneEstudiantePorIdCorrectamente() {
        Estudiante estudiante = mockEstudiante();
        mockEstudianteRepositoryFindById(estudianteRepository, estudiante);
        Optional<Estudiante> result = estudianteService.findById(1);
        assertFalse(result.isEmpty());
        verify(estudianteRepository).findById(anyInt());
    }

    @Test
    @DisplayName("Al intentar obtener estudiante con id null")
    void alIntentarObtenerEstudianteConIdNull() {
        Optional<Estudiante> result = estudianteService.findById(null);
        assertTrue(result.isEmpty());
        verify(estudianteRepository, times(0)).findById(anyInt());
    }

    @Test
    @DisplayName("Listar todos los estudiantes")
    void listarTodosLosEstudiantes() {
        List<Estudiante> estudiantes = new ArrayList<>();
        estudiantes.add(mockEstudiante());
        mockEstudianteRepositoryFindAll(estudianteRepository, estudiantes);
        List<Estudiante> result = estudianteService.findAll();
        assertFalse(result.isEmpty());
        assertEquals(1, result.size());
        verify(estudianteRepository).findAll();
    }
}
