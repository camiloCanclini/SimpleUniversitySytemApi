package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Business.Interfaces.ProfesorBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Implementations.ProfesorData;
import com.canclini.finalLaboIII.Entity.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Service
public class ProfesorBusiness implements ProfesorBusinessInterface {
    @Autowired
    ProfesorData profesorData;
    @Override
    public int crearProfesor(ProfesorDto profesor) {
        Profesor nuevoProfesor = new Profesor();
        nuevoProfesor.setNombre(profesor.getNombre());
        nuevoProfesor.setApellido(profesor.getApellido());
        nuevoProfesor.setDni(profesor.getDni());
        nuevoProfesor.setTitulo(profesor.getTitulo());
        nuevoProfesor.setMateriasDictadas( new HashSet<Integer>());
        return profesorData.crearProfesor(nuevoProfesor);
    }

    @Override
    public void borrarProfesor(int idProfesor) throws ProfesorNoEncontradoException {
        profesorData.borrarProfesor(idProfesor);
    }

    @Override
    public void agregarMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException {
        profesorData.agregarMateria(idProfesor, idMateria);
    }

    public void borrarMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException {
        profesorData.borrarMateria(idProfesor, idMateria);
    }

    @Override
    public Profesor buscarProfesorById(int idProfesor) throws ProfesorNoEncontradoException {
        return profesorData.buscarProfesorById(idProfesor);
    }

    @Override
    public Map<Integer, Profesor> obtenerListaProfesor() throws NoHayProfesoresException {
        return profesorData.obtenerListaProfesor();
    }
}
