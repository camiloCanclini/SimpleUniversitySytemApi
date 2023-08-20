package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Interfaces.ProfesorDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Materia;
import com.canclini.finalLaboIII.Entity.Profesor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Repository
public class ProfesorData extends MemoryDataAbstract<Profesor> implements ProfesorDataInterface{

    @Autowired
    MateriaData materiaData;
    @Override
    public int crearProfesor(Profesor profesor) {
        int idProfesor = generarId();
        lista.put(idProfesor, profesor);
        return idProfesor;
    }

    @Override
    public void borrarProfesor(int idProfesor) throws ProfesorNoEncontradoException, NoHayProfesoresException {
        if (!obtenerListaProfesor().containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        lista.remove(idProfesor);
    }

    @Override
    public void agregarMateria(int idProfesor, Integer idMateria) throws ProfesorNoEncontradoException, NoHayMateriasException, MateriaNoEncontradaException, NoHayProfesoresException {
        if (!obtenerListaProfesor().containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        if (!materiaData.obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        obtenerListaProfesor().get(idProfesor).getMateriasDictadas().add(idMateria);
    }
    @Override
    public void borrarMateria(int idProfesor, Integer idMateria) throws ProfesorNoEncontradoException, NoHayMateriasException, MateriaNoEncontradaException {
        if (!materiaData.obtenerListaMaterias().containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        if (!materiaData.obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        lista.get(idProfesor).getMateriasDictadas().remove(idMateria);
    }
    @Override
    public Profesor buscarProfesorById(int idProfesor) throws ProfesorNoEncontradoException, NoHayProfesoresException {
        if (!obtenerListaProfesor().containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        return obtenerListaProfesor().get(idProfesor);
    }

    @Override
    public Map<Integer, Profesor> obtenerListaProfesor() throws NoHayProfesoresException {
        if (lista.isEmpty()) {
            throw new NoHayProfesoresException();
        }
        return lista;
    }
}
