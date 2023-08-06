package com.canclini.finalLaboIII.Business.Dtos.Materia;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MateriaDto {
    @NotBlank(message = "El nombre no puede ser en blanco")
    @NotNull(message = "El nombre no puede ser NULL")
    private String nombre;

    @Min(value = 1, message = "La cantidad de años debe ser mayor cero")
    @NotNull(message = "La cantidad de años no puede ser NULL")
    private Integer anio;

    @Min(value = 1, message = "La cantidad de cuatrimestres debe ser mayor cero")
    @NotNull(message = "La cantidad de cuatrimestres no puede ser NULL")
    private Integer cuatrimestre;


}
