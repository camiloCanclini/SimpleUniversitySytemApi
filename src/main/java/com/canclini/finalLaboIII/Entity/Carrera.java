package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
public class Carrera {
    private String nombre;
    private int cantidadAnios;
    private List<Materia> materiasList;
}
