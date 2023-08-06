package com.canclini.finalLaboIII.Business.Dtos.Alumno;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AlumnoEditarDto {

    @NotBlank(message = "El nombre no puede ser en blanco")
    private String nombre;

    @NotBlank(message = "El apellido no puede ser en blanco")
    private String apellido;

}
