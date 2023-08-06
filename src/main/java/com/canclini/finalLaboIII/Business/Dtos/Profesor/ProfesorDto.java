package com.canclini.finalLaboIII.Business.Dtos.Profesor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorDto {

    @NotBlank(message = "El nombre no puede ser en blanco")
    @NotNull(message = "El nombre no puede ser NULL")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser en blanco")
    @NotNull(message = "El apellido no puede ser NULL")
    private String apellido;

    @Min(1)
    @NotNull(message = "El DNI no puede ser NULL")
    private Long dni;

    @NotBlank(message = "El titulo no puede ser en blanco")
    @NotNull(message = "El titulo no puede ser NULL")
    private String titulo;
}
