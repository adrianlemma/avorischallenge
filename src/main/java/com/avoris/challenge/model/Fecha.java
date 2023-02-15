package com.avoris.challenge.model;

import com.avoris.challenge.enumerator.Mes;
import com.avoris.challenge.exception.FechaFinalizacionException;
import jakarta.persistence.*;

@Entity
@Table(name = "fecha")
public final class Fecha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Integer dia;

    @Column
    private Mes mes;

    @Column
    private Integer anio;

    public Fecha(Integer dia, Integer mes, Integer anio) {
        if (!añoValido(anio)) {
            throw new FechaFinalizacionException("Año", anio);
        }
        if (!mesValido(mes)) {
            throw new FechaFinalizacionException("Mes", mes);
        }
        if (!diaValido(dia, mes, anio)) {
            throw new FechaFinalizacionException("Día", dia);
        }
        this.anio = anio;
        this.mes = Mes.values()[mes - 1];
        this.dia = dia;
    }

    public Fecha() { }

    private Boolean añoValido(Integer anio) {
        return anio != null && (anio >= 1500 && anio <= 3000);
    }

    private Boolean mesValido(Integer mes) {
        return mes != null && (mes >= 1 && mes <= 12);
    }

    private Boolean diaValido(Integer dia, Integer mes, Integer anio) {
        Integer limite = 28;
        if (mes == 1 || mes == 3 || mes == 5 || mes == 7 || mes == 8 || mes == 10 || mes == 12) {
            limite = 31;
        }
        if (mes == 4 || mes == 6 || mes == 9 || mes == 11) {
            limite = 30;
        }
        if (mes == 2 && (anio % 400 == 0 || (anio % 4 == 0 && anio % 100 != 0))) {
            limite = 29;
        }
        return dia != null && (dia > 0 && dia <= limite);
    }

    public String getFecha() {
        return dia + " de " + mes.getNombre() + " de " + anio;
    }
}
