package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Interfaces.CarreraDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Carrera;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Repository
public class CarreraData extends MemoryDataAbstract<Carrera> implements CarreraDataInterface {

    @Override
    public int crearCarrera(Carrera carrera) {
        int idCarrera = generarId();
        lista.put(idCarrera, carrera);
        return idCarrera;
    }

    @Override
    public void borrarCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.remove(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, Carrera carrera) throws NoHayCarrerasException, CarreraNoEncontradaException {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        lista.replace(idCarrera,carrera);
    }

    @Override
    public Carrera buscarCarreraById(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        return (Carrera) lista.get(idCarrera);
    }

    @Override
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        Carrera carrera = lista.get(idCarrera);
        return carrera.getMateriasList();
    }

    @Override
    public Map<Integer, Carrera> obtenerListaCarrera() throws NoHayCarrerasException {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        return lista;
    }
}
