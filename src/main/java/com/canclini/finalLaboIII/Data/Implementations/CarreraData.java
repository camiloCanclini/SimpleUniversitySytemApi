package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Interfaces.CarreraDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
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
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.remove(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, Carrera carrera) {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.replace(idCarrera,carrera);
    }

    @Override
    public Object buscarCarreraById(int idCarrera) {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        return lista.get(idCarrera);
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriaDeCarrera(int idCarrera) {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        Carrera carrera = (Carrera) lista.get(idCarrera);
        return carrera.getMateriasList();
    }

    @Override
    public Map obtenerListaCarrera() {
        return lista;
    }
}
