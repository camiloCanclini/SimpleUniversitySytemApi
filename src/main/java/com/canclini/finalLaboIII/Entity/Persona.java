package com.canclini.finalLaboIII.Entity;

import lombok.*;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public abstract class Persona {
    private String nombre;
    private String apellido;
    private Long dni;
}
