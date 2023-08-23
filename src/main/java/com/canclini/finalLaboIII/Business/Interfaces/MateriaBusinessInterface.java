package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface MateriaBusinessInterface {
    public int crearMateria(MateriaDto materia);
    public void borrarMateria(int idMateria) throws MateriaNoEncontradaException, NoHayMateriasException;
    public void editarMateria(int idMateria, MateriaDto materia) throws MateriaNoEncontradaException, NoHayMateriasException;
    public Materia buscarMateriaById(int idMateria) throws MateriaNoEncontradaException, NoHayMateriasException;
    public Materia buscarMateriaByNombre(String nombreMateria) throws MateriaNoEncontradaException, NoHayMateriasException;
    public void agregarProfesorAMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, NoHayMateriasException, NoHayProfesoresException, ProfesorNoEncontradoException;
    public void sacarProfesorDeMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException, NoHayProfesoresException;
    public Map<Integer, Materia> obtenerListaMaterias() throws NoHayMateriasException;
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy order) throws NoHayMateriasException;
}
