package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Business.Interfaces.MateriaBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Data.Implementations.ProfesorData;
import com.canclini.finalLaboIII.Entity.Materia;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
@Service
public class MateriaBusiness implements MateriaBusinessInterface {
    @Autowired
     MateriaData materiaData;

    @Autowired
    ProfesorData profesorData;

    @Override
    public int crearMateria(@NonNull MateriaDto materia) {
        Materia nuevaMateria = new Materia(materia.getNombre(), materia.getAnio(), materia.getCuatrimestre(), null, new HashSet<>());
        return materiaData.crearMateria(nuevaMateria);
    }

    @Override
    public void borrarMateria(int idMateria) throws MateriaNoEncontradaException, NoHayMateriasException {
        materiaData.borrarMateria(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, MateriaDto materia) throws MateriaNoEncontradaException, NoHayMateriasException {
        materiaData.editarMateria(idMateria, new Materia(materia.getNombre(), materia.getAnio(), materia.getCuatrimestre(), null, new HashSet<>()));
    }

    @Override
    public Materia buscarMateriaById(int idMateria) throws MateriaNoEncontradaException, NoHayMateriasException {
        return materiaData.buscarMateriaById(idMateria);
    }

    @Override
    public Materia buscarMateriaByNombre(String nombreMateria) throws MateriaNoEncontradaException, NoHayMateriasException {
        return materiaData.buscarMateriabyNombre(nombreMateria);
    }

    @Override
    public void agregarProfesorAMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, NoHayMateriasException, NoHayProfesoresException, ProfesorNoEncontradoException {
        if (!materiaData.obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        if (!profesorData.obtenerListaProfesor().containsKey(idProfesor)) {
            throw new ProfesorNoEncontradoException();
        }
        materiaData.agregarProfesorAMateria(idProfesor, idMateria);
    }

    @Override
    public void sacarProfesorDeMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException, NoHayProfesoresException {
        if (!materiaData.obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }

        materiaData.sacarProfesorDeMateria(idProfesor, idMateria);
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() throws NoHayMateriasException {
        return materiaData.obtenerListaMaterias();
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy order) throws NoHayMateriasException {
        return materiaData.obtenerListaMateriasOrderedBy(order);
    }

}
