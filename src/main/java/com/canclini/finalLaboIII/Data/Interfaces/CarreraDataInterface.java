package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Entity.Carrera;

import java.util.HashSet;
import java.util.Map;

public interface CarreraDataInterface {
    public int crearCarrera(Carrera carrera);
    public void borrarCarrera(int idCarrera);
    public void editarCarrera(int idCarrera, Carrera carrera);
    public Object buscarCarreraById(int idCarrera);
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera);
    public Map<Integer, Carrera> obtenerListaCarrera();
}
