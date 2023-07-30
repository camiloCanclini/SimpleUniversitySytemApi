package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface MateriaBusinessInterface {
    public int crearMateria(Materia materia);
    public void borrarMateria(int idMateria);
    public void editarMateria(int idMateria,Materia materia);
    public Materia buscarMateriaById(int idMateria);
    public Materia buscarMateriaByNombre(String nombreMateria);
    public Map<Integer, Materia> obtenerListaMaterias();
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy order);
}
