package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    private String nombre;
    private int anio;
    private int cuatrimestre;
    private Profesor profesor;
    private List<Materia> correlatividades;
    public void agregarCorrelatividad(Materia m){
        this.correlatividades.add(m);

    }
}
