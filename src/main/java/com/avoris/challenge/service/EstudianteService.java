package com.avoris.challenge.service;

import com.avoris.challenge.model.Estudiante;
import java.util.List;
import java.util.Optional;

public interface EstudianteService {

    Estudiante save(Estudiante estudiante);

    Optional<Estudiante> findById(Integer id);

    List<Estudiante> findAll();
}
