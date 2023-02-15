package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Fecha;
import com.avoris.challenge.repository.FechaRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static com.avoris.challenge.mocks.Mock.mockFecha;
import static com.avoris.challenge.mocks.Mock.mockFechaRepositorySave;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

class FechaServiceImplTest {

    @Mock
    FechaRepository fechaRepository;

    @InjectMocks
    FechaServiceImpl fechaService;

    @BeforeEach
    void setup() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("Persiste fecha correctamente")
    void persisteFechaCorrectamente() {
        Fecha fecha = mockFecha();
        mockFechaRepositorySave(fechaRepository, fecha);
        Fecha result = fechaService.save(fecha);
        assertEquals(fecha.getFecha(), result.getFecha());
        verify(fechaRepository).save(any());
    }

    @Test
    @DisplayName("Al intentar persistir null")
    void alIntentarPersistirNull() {
        Fecha result = fechaService.save(null);
        assertNull(result);
        verify(fechaRepository, times(0)).save(any());
    }
}
