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
public class Alumno {
    private long id;
    private String nombre;
    private String apellido;
    private long dni;
    private List<Asignatura> asignaturas;
}
