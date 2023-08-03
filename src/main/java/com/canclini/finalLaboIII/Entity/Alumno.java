package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashMap;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Alumno {
    private String nombre;
    private String apellido;
    private long dni;
    private HashMap<Integer,Asignatura> asignaturas = new HashMap<>();
}
