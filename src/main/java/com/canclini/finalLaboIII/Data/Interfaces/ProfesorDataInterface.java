package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Profesor;

import java.util.Map;

public interface ProfesorDataInterface {
    public int crearProfesor(Profesor profesor);
    public void borrarProfesor(int idProfesor) throws ProfesorNoEncontradoException, NoHayProfesoresException;
    public Profesor buscarProfesorById(int idProfesor) throws ProfesorNoEncontradoException, NoHayProfesoresException;
    public Map<Integer, Profesor> obtenerListaProfesor() throws NoHayProfesoresException;
}
