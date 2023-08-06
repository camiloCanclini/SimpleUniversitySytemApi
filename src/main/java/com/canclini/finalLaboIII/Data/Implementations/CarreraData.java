package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Interfaces.CarreraDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Carrera;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;

@Service
public class CarreraData extends MemoryDataAbstract<Carrera> implements CarreraDataInterface {

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
    public Carrera buscarCarreraById(int idCarrera) {
        if (lista.isEmpty()) {
            throw new NoHayCarrerasException();
        }
        if (!lista.containsKey(idCarrera)) {
            throw new CarreraNoEncontradaException();
        }
        return (Carrera) lista.get(idCarrera);
    }

    @Override
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera) {
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
    public Map<Integer, Carrera> obtenerListaCarrera() {
        return lista;
    }
}
