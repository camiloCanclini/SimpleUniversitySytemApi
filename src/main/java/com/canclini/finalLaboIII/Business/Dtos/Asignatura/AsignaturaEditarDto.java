package com.canclini.finalLaboIII.Business.Dtos.Asignatura;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AsignaturaEditarDto {
    @NotNull(message = "El estado no puede ser NULL")
    @NotBlank(message = "El estado no puede estar en blanco")
    private String estado;

    @Min(value = 1, message = "La nota debe ser mayor cero")
    @Max(value = 10, message = "La nota no puede ser mayor a diez")
    @NotNull(message = "La nota no puede ser NULL")
    private Integer nota;
}
