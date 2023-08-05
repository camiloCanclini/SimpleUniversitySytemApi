package com.canclini.finalLaboIII.Business.Dtos;

import com.canclini.finalLaboIII.Entity.Materia;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CarreraDto {
    @NotBlank(message = "El nombre no puede ser en blanco")
    @NotNull(message = "El nombre no puede ser NULL")
    private String nombre;

    @Min(value = 1, message = "La cantidad de años debe ser mayor cero")
    @NotNull(message = "Cantidad de años no puede NULL")
    private Integer cantidadAnios;

    @NotNull(message = "El ID del departamento no puede ser NULL")
    private Integer departamentoId;
}
