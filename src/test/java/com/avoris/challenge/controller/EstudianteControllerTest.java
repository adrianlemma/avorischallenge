package com.avoris.challenge.controller;

import com.avoris.challenge.component.EstudianteRequestToEstudiante;
import com.avoris.challenge.exception.EstudianteException;
import com.avoris.challenge.exception.FechaFinalizacionException;
import com.avoris.challenge.exception.MateriaException;
import com.avoris.challenge.model.request.EstudianteRequest;
import com.avoris.challenge.service.EstudianteService;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.ArrayList;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static com.avoris.challenge.constant.Constant.EDAD_OK;
import static com.avoris.challenge.constant.Constant.NOMBRE_ESTUDIANTE_OK;
import static com.avoris.challenge.mocks.Mock.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(EstudianteController.class)
class EstudianteControllerTest {

    @MockBean
    EstudianteService estudianteService;

    @MockBean
    EstudianteRequestToEstudiante estudianteRequestToEstudiante;

    @Autowired
    MockMvc mvc;

    private final ObjectMapper mapper = new ObjectMapper();

    @Nested
    @DisplayName("Tests para el metodo GET que retorna la lista de estudiantes")
    class testGetListMethod {
        @Test
        @DisplayName("Lista todos los estudiantes correctamente")
        void listaTodosLosEstudiantesCorrectamente() throws Exception {
            mockEstudianteServiceFindAll(estudianteService, Lists.newArrayList(mockEstudiante()));

            mvc.perform(MockMvcRequestBuilders
                            .get("/avoris/estudiante/list")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$[0].nombre").value(NOMBRE_ESTUDIANTE_OK))
                    .andExpect(jsonPath("$[0].edad").value(EDAD_OK));
        }

        @Test
        @DisplayName("no se encuentran estudiantes para listar")
        void noSeEncuentranEstudiantesParaListar() throws Exception {
            mockEstudianteServiceFindAll(estudianteService, new ArrayList<>());

            mvc.perform(MockMvcRequestBuilders
                            .get("/avoris/estudiante/list")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }
    }

    @Test
    @DisplayName("obtener estudiante por id correctamente")
    void obtenerEstudiantePorIdCorrectamente() throws Exception {
        mockEstudianteServiceFindById(estudianteService, mockEstudiante());

        mvc.perform(MockMvcRequestBuilders
                        .get("/avoris/estudiante/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.nombre").value(NOMBRE_ESTUDIANTE_OK))
                .andExpect(jsonPath("$.edad").value(EDAD_OK));
    }

    @Nested
    @DisplayName("Tests para el metodo GET que retorna un estudiante segun su id")
    class testGetByIdMethod {
        @Test
        @DisplayName("no se encuentra estudiante por id")
        void noSeEncuentraEstudiantePorId() throws Exception {
            mockEstudianteServiceFindById(estudianteService, null);

            mvc.perform(MockMvcRequestBuilders
                            .get("/avoris/estudiante/1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isNotFound());
        }

        @Test
        @DisplayName("no se encuentra estudiante por id 2")
        void noSeEncuentraEstudiantePorId2() throws Exception {
            mvc.perform(MockMvcRequestBuilders
                            .get("/avoris/estudiante/-1")
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }

    @Nested
    @DisplayName("Tests para el metodo POST que persiste estudiantes")
    class testSavePostMethod {
        @Test
        @DisplayName("persistir estudiante correctamente")
        void persistirEstudianteCorrectamente() throws Exception {
            EstudianteRequest request = mockEstudianteRequest();
            mockEstudianteServiceSave(estudianteService, mockEstudiante());
            mockEstudianteRequestToEstudiante(estudianteRequestToEstudiante, mockEstudiante());

            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isOk())
                    .andExpect(jsonPath("$.nombre").value(NOMBRE_ESTUDIANTE_OK))
                    .andExpect(jsonPath("$.edad").value(EDAD_OK));
        }

        @Test
        @DisplayName("falla intentar persistir request invalido")
        void fallaAlIntentarPersistirRequestInvalido() throws Exception {
            EstudianteRequest request = new EstudianteRequest(" ",
                    EDAD_OK, mockFechaRequest(), Lists.newArrayList(mockMateriaRequest()));
            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("falla durante la persistencia")
        void fallDuranteLaPersistencia() throws Exception {
            EstudianteRequest request = mockEstudianteRequest();
            mockEstudianteServiceSave(estudianteService, null);
            mockEstudianteRequestToEstudiante(estudianteRequestToEstudiante, mockEstudiante());

            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isInternalServerError());
        }
    }

    @Nested
    @DisplayName("Tests para cubrir validaciones de constructores")
    class testDataValidation {
        @Test
        @DisplayName("cundo la fecha es invalida arroja fecha exception")
        void cundoLaFechaEsInvalidaArrojaFechaException() throws Exception {
            EstudianteRequest request = mockEstudianteRequest();
            mockEstudianteRequestToEstudianteThrowsException(estudianteRequestToEstudiante, new FechaFinalizacionException("Dia", -1));

            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("cundo la materia es invalida arroja materia exception")
        void cundoLaMateriaEsInvalidaArrojaMateriaException() throws Exception {
            EstudianteRequest request = mockEstudianteRequest();
            mockEstudianteRequestToEstudianteThrowsException(estudianteRequestToEstudiante, new MateriaException("Nombre", null));

            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }

        @Test
        @DisplayName("cundo el estudiante es invalido arroja estudiante exception")
        void cundoElEstudianteEsInvalidoArrojaEstudianteException() throws Exception {
            EstudianteRequest request = mockEstudianteRequest();
            mockEstudianteRequestToEstudianteThrowsException(estudianteRequestToEstudiante, new EstudianteException("Nombre", null));

            mvc.perform(post("/avoris/estudiante/save")
                            .content(mapper.writeValueAsString(request))
                            .contentType(MediaType.APPLICATION_JSON)
                            .accept(MediaType.APPLICATION_JSON))
                    .andExpect(status().isBadRequest());
        }
    }
}
