package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Entity.Profesor;

import java.util.Map;

public interface ProfesorBusinessInterface {
    public int crearProfesor(ProfesorDto profesor);
    public void borrarProfesor(int idProfesor) throws ProfesorNoEncontradoException;
    public void agregarMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException;
    public void borrarMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException;
    public Profesor buscarProfesorById(int idProfesor) throws ProfesorNoEncontradoException;
    public Map<Integer, Profesor> obtenerListaProfesor() throws NoHayProfesoresException;
}
