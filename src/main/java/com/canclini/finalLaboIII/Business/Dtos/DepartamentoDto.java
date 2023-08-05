package com.canclini.finalLaboIII.Business.Dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DepartamentoDto {
    @NotBlank(message = "El nombre no puede ser en blanco")
    @NotNull(message = "El nombre no puede ser NULL")
    private String nombre;
}
