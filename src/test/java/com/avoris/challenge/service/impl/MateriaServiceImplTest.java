package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Materia;
import com.avoris.challenge.repository.MateriaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.avoris.challenge.mocks.Mock.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class MateriaServiceImplTest {

    @Mock
    MateriaRepository materiaRepository;

    @InjectMocks
    MateriaServiceImpl materiaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Persiste materia correctamente")
    void persisteMateriaCorrectamente() {
        Materia materia = mockMateria();
        mockMateriaRepositorySave(materiaRepository, materia);
        Materia result = materiaService.save(materia);
        assertEquals(materia.getNombre(), result.getNombre());
        assertEquals(materia.getCalificacion(), result.getCalificacion());
        verify(materiaRepository).save(any());
    }

    @Test
    @DisplayName("Al intentar persistir null")
    void alIntentarPersistirNull() {
        Materia result = materiaService.save(null);
        assertNull(result);
        verify(materiaRepository, times(0)).save(any());
    }
}
