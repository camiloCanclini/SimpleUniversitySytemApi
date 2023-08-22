package com.canclini.finalLaboIII.Entity;

import lombok.*;

import java.util.HashSet;
import java.util.List;
import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Carrera {
    private String nombre;
    private int cantidadAnios;
    private int departamentoId;
    private HashSet<Integer> materiasList; // Contiene los Ids de las materias

}
