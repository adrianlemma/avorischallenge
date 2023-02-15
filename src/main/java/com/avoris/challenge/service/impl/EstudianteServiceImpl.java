package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Estudiante;
import com.avoris.challenge.repository.EstudianteRepository;
import com.avoris.challenge.service.EstudianteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EstudianteServiceImpl implements EstudianteService {

    @Autowired
    EstudianteRepository estudianteRepository;

    @Override
    public Estudiante save(Estudiante estudiante) {
        if (estudiante != null) {
            return estudianteRepository.save(estudiante);
        }
        return null;
    }

    @Override
    public Optional<Estudiante> findById(Integer id) {
        if (id != null) {
            return estudianteRepository.findById(id);
        }
        return Optional.empty();
    }

    @Override
    public List<Estudiante> findAll() {
        return estudianteRepository.findAll();
    }
}
