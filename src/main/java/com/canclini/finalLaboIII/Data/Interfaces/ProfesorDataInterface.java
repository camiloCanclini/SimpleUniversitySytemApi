package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Profesor;

import java.util.Map;

public interface ProfesorDataInterface {
    public int crearProfesor(Profesor profesor);
    public void borrarProfesor(int idProfesor);
    public void editarProfesor(int idProfesor, Profesor profesor);
    public Object buscarProfesorById(int idProfesor);
    public Map<Integer, Profesor> obtenerListaProfesor();
}
