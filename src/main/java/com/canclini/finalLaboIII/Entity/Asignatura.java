package com.canclini.finalLaboIII.Entity;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Asignatura {
    public enum Estado{
        NO_CURSADA,
        CURSADA,
        APROBADA
    }
    private Integer idMateria;
    private Estado estado;
    private Integer nota;
}
