package com.canclini.finalLaboIII.Business.Dtos;

import com.canclini.finalLaboIII.Entity.Asignatura;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignaturaDto {

    @NotNull(message = "El id de la materia no puede ser NULL")
    private Integer idMateria;

    @NotNull(message = "El estado no puede ser NULL")
    private Asignatura.Estado estado;

    @Min(value = 1, message = "La nota debe ser mayor cero")
    @NotNull(message = "La nota no puede ser NULL")
    private Integer nota;
}
