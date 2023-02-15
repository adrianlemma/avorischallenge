package com.avoris.challenge.repository;

import com.avoris.challenge.model.Fecha;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FechaRepository extends JpaRepository<Fecha, Integer> {
}
