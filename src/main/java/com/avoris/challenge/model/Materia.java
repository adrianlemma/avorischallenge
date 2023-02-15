package com.avoris.challenge.model;

import com.avoris.challenge.exception.MateriaException;
import jakarta.persistence.*;

@Entity
@Table(name = "materia")
public final class Materia {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private Double calificacion;

    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "estudiante_id", nullable = false)
    private Estudiante estudiante;

    public Materia(String nombre, Double calificacion, Estudiante estudiante) {
        if (!nombreValido(nombre)) {
            throw new MateriaException("nombre", nombre);
        }
        if(!calificacionValida(calificacion)) {
            throw new MateriaException("calificacion", String.valueOf(calificacion));
        }
        this.calificacion = calificacion;
        this.nombre = nombre;
        this.estudiante = estudiante;
    }

    public Materia() { }

    private Boolean nombreValido(String nombre) {
        return nombre != null && !nombre.isBlank();
    }

    private Boolean calificacionValida(Double calificacion) {
        return calificacion != null && (calificacion >= 0 && calificacion <= 10);
    }

    public String getNombre() {
        return nombre;
    }

    public Double getCalificacion() {
        return calificacion;
    }

}
