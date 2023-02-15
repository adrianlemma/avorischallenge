package com.avoris.challenge.service.impl;

import com.avoris.challenge.model.Fecha;
import com.avoris.challenge.repository.FechaRepository;
import com.avoris.challenge.service.FechaService;
import org.springframework.stereotype.Service;

@Service
public class FechaServiceImpl implements FechaService {

    private final FechaRepository fechaRepository;

    public FechaServiceImpl(FechaRepository fechaRepository) {
        this.fechaRepository = fechaRepository;
    }

    @Override
    public Fecha save(Fecha fecha) {
        if (fecha != null) {
            return fechaRepository.save(fecha);
        }
        return null;
    }
}
