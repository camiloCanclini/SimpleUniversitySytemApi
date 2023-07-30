package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Entity.Materia;

import java.util.Map;

public interface MateriaDataInterface {
    public int crearMateria(Materia materia);
    public void borrarMateria(int idMateria);
    public void editarMateria(int idMateria,Materia materia);
    public Materia buscarMateriaById(int idMateria);
    public Map<Integer, Materia> obtenerListaMaterias();

}
