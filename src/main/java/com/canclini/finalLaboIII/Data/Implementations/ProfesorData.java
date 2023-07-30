package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Interfaces.ProfesorDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Profesor;

import java.util.Map;

public class ProfesorData extends MemoryDataAbstract implements ProfesorDataInterface{
    @Override
    public int crearProfesor(Profesor profesor) {
        int idProfesor = generarId();
        lista.put(idProfesor, profesor);
        return idProfesor;
    }

    @Override
    public void borrarProfesor(int idProfesor) {
        if (!lista.containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        lista.remove(idProfesor);
    }

    @Override
    public void editarProfesor(int idProfesor, Profesor profesor) {
        if (!lista.containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        lista.replace(idProfesor, profesor);
    }

    @Override
    public Object buscarProfesorById(int idProfesor) {
        if (!lista.containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        return lista.get(idProfesor);
    }

    @Override
    public Map<Integer, Profesor> obtenerListaProfesor() {
        return lista;
    }
}
