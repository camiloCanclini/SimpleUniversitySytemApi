package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData.OrderMateriaBy;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface MateriaDataInterface {
    public int crearMateria(Materia materia);
    public void borrarMateria(int idMateria) throws NoHayMateriasException, MateriaNoEncontradaException;
    public void editarMateria(int idMateria,Materia materia) throws NoHayMateriasException, MateriaNoEncontradaException;
    public Materia buscarMateriaById(int idMateria) throws NoHayMateriasException, MateriaNoEncontradaException;
    public Materia buscarMateriabyNombre(String nombreMateria) throws NoHayMateriasException, MateriaNoEncontradaException;
    public Map<Integer, Materia> obtenerListaMaterias() throws NoHayMateriasException;
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(OrderMateriaBy order) throws NoHayMateriasException;
    public void agregarProfesorAMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, NoHayMateriasException, NoHayProfesoresException;
    public void sacarProfesorDeMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException, NoHayProfesoresException;

}
