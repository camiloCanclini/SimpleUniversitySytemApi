package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
public class Carrera {
    private String nombre;
    private int cantidadAnios;
    private List<Map.Entry<Integer, Materia>>  materiasList; // Contiene los Ids de las materias
    private int departamentoId;
}
