package com.canclini.finalLaboIII.Business.Dtos.Profesor;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProfesorMateriaDto {
    @NotNull
    @Min(1)
    Integer idMateria;
}
