package com.canclini.finalLaboIII.Data.Exceptions;

import lombok.experimental.StandardException;

@StandardException
public class NoHayAlumnosException extends Exception {
    public NoHayAlumnosException() {
        super("El Alumno no fue encontrado");
    }
}
