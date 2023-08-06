package com.canclini.finalLaboIII.Business.Dtos.Asignatura;

import com.canclini.finalLaboIII.Entity.Asignatura;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignaturaDto {

    @NotNull(message = "El id de la materia no puede ser NULL")
    @Min(1)
    private Integer idMateria;

}
