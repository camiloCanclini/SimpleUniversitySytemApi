package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Implementations.MateriaData.OrderMateriaBy;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface MateriaDataInterface {
    public int crearMateria(Materia materia);
    public void borrarMateria(int idMateria);
    public void editarMateria(int idMateria,Materia materia) ;
    public Materia buscarMateriaById(int idMateria) ;
    public Materia buscarMateriabyNombre(String nombreMateria);
    public Map<Integer, Materia> obtenerListaMaterias();
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(OrderMateriaBy order);

}
