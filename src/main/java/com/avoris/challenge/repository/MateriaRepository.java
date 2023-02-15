package com.avoris.challenge.repository;

import com.avoris.challenge.model.Materia;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MateriaRepository extends JpaRepository<Materia, Integer> {
}
