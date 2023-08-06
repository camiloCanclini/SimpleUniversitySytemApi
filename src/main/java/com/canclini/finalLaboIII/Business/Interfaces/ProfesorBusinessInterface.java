package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Entity.Profesor;

import java.util.Map;

public interface ProfesorBusinessInterface {
    public int crearProfesor(ProfesorDto profesor);
    public void borrarProfesor(int idProfesor);
    public void agregarMateria(int idProfesor, Integer idMateria);
    public void borrarMateria(int idProfesor, Integer idMateria);
    public Profesor buscarProfesorById(int idProfesor);
    public Map<Integer, Profesor> obtenerListaProfesor();
}
