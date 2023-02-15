package com.avoris.challenge.model;

import com.avoris.challenge.exception.EstudianteException;
import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "estudiante")
public final class Estudiante {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @Column
    private String nombre;

    @Column
    private Integer edad;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "fecha_finalizacion_id", referencedColumnName = "id")
    private Fecha fechaFinalizacion;

    @OneToMany(mappedBy = "estudiante", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<Materia> materiasCursadas;

    public Estudiante(String nombre, Integer edad, Fecha fechaFinalizacion, List<Materia> materiasCursadas) {
        if (!nombreValido(nombre)) {
            throw new EstudianteException("Nombre", nombre);
        }
        if (!edadValida(edad)) {
            throw new EstudianteException("Edad", String.valueOf(edad));
        }
        if (fechaFinalizacion == null) {
            throw new EstudianteException("FechaFinalizacion", "null");
        }
        if (materiasCursadas == null) {
            throw new EstudianteException("MateriasCursadas", "null");
        }
        this.edad = edad;
        this.nombre = nombre;
        this.fechaFinalizacion = fechaFinalizacion;
        List<Materia> materiasCopy = new ArrayList<>();
        materiasCursadas.forEach(materia ->
                materiasCopy.add(new Materia(materia.getNombre(), materia.getCalificacion(), this)));
        this.materiasCursadas = materiasCopy;
    }

    public Estudiante() { }

    private Boolean nombreValido(String nombre) {
        return nombre != null && !nombre.isBlank();
    }

    private Boolean edadValida(Integer edad) {
        return edad != null && (edad >= 6 && edad <= 99);
    }

    public String getNombre() {
        return nombre;
    }

    public Integer getEdad() {
        return edad;
    }

    public Fecha getFechaFinalizacion() {
        return fechaFinalizacion;
    }

    public List<Materia> getMateriasCursadas() {
        return new ArrayList<>(materiasCursadas);
    }
}
