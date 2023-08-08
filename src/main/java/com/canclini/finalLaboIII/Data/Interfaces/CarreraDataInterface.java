package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Entity.Carrera;

import java.util.HashSet;
import java.util.Map;

public interface CarreraDataInterface {
    public int crearCarrera(Carrera carrera);
    public void borrarCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public void editarCarrera(int idCarrera, Carrera carrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public Object buscarCarreraById(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public Map<Integer, Carrera> obtenerListaCarrera() throws NoHayCarrerasException;
}
