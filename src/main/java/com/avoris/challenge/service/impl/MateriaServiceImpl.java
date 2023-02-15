package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Materia;
import com.avoris.challenge.repository.MateriaRepository;
import com.avoris.challenge.service.MateriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class MateriaServiceImpl implements MateriaService {

    @Autowired
    MateriaRepository materiaRepository;

    @Override
    public Materia save(Materia materia) {
        if (materia != null) {
            return materiaRepository.save(materia);
        }
        return null;
    }
}
