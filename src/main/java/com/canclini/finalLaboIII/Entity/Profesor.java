package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
@Getter
@Setter
@AllArgsConstructor
public class Profesor {
    private final String nombre;
    private final String apellido;
    private final String titulo;
    private List<Materia> materiasDictadas;
}
