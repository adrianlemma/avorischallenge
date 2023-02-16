package com.avoris.challenge.controller;

import com.avoris.challenge.component.EstudianteRequestToEstudiante;
import com.avoris.challenge.exception.ApiException;
import com.avoris.challenge.exception.ValidationException;
import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.model.request.EstudianteRequest;
import com.avoris.challenge.service.EstudianteService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/avoris/estudiante")
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
public class EstudianteController {

    @Autowired
    EstudianteService estudianteService;

    @Autowired
    EstudianteRequestToEstudiante estudianteRequestToEstudiante;

    @GetMapping(path = "/list")
    public ResponseEntity<Object> getEnterprises() {
        List<Estudiante> estudiantes = estudianteService.findAll();
        if (estudiantes.isEmpty()) {
            throw new ApiException("No se encontraron estudiantes en la base de datos", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estudiantes);
    }

    @GetMapping(path = "/{estudiante-id}")
    public ResponseEntity<Object> getEnterprises(@PathVariable ("estudiante-id") Integer estudianteId) {
        if (estudianteId == null || estudianteId < 1) {
            throw new ValidationException("estudiante-id", "Ingrese un ID válido para la búsqueda");
        }
        Optional<Estudiante> estudiante = estudianteService.findById(estudianteId);
        if (estudiante.isEmpty()) {
            throw new ApiException("Estudiante no encontrado", HttpStatus.NOT_FOUND);
        }
        return ResponseEntity.ok(estudiante.get());
    }

    @PostMapping(path = "/save")
    public ResponseEntity<Object> setConfig(@Valid @RequestBody EstudianteRequest request, BindingResult bindingResult) {
        if (bindingResult.hasErrors() || request == null) {
            throw new ValidationException(Objects.requireNonNull(bindingResult.getFieldError()).getField(),
                    bindingResult.getFieldError().getDefaultMessage());
        }
        Estudiante estudiante = estudianteRequestToEstudiante.execute(request);
        Estudiante result = estudianteService.save(estudiante);
        if (result == null) {
            throw new ApiException("Falla al persistir los datos", HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return ResponseEntity.ok(result);
    }
}
