package com.canclini.finalLaboIII.Entity;

import lombok.*;
import org.springframework.lang.NonNull;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Materia {
    private String nombre;
    private Integer anio;
    private Integer cuatrimestre;
    private Profesor profesor;
    private List<Materia> correlatividades;
}
