package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Interfaces.CarreraDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Carrera;

import java.util.Map;

public class CarreraData extends MemoryDataAbstract implements CarreraDataInterface {
    @Override
    public int crearCarrera(Carrera carrera) {
        int idCarrera = generarId();
        lista.put(idCarrera, carrera);
        return idCarrera;
    }

    @Override
    public void borrarCarrera(int idCarrera) {
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.remove(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, Carrera carrera) {
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.replace(idCarrera,carrera);
    }

    @Override
    public Object buscarCarreraById(int idCarrera) {
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        return lista.get(idCarrera);
    }

    @Override
    public Map obtenerListaCarrera() {
        return lista;
    }
}
