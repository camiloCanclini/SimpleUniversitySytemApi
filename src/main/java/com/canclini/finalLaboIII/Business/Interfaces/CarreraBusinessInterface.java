package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface CarreraBusinessInterface {
    public int crearCarrera(Carrera carrera);
    public void borrarCarrera(int idCarrera);
    public void editarCarrera(int idCarrera, Carrera carrera);
    public Object buscarCarreraById(int idCarrera);
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriaDeCarrera(int idCarrera);
    public Map<Integer, Integer> obtenerListaCarrera();
}
