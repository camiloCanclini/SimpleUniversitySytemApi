package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Interfaces.MateriaBusinessInterface;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;
import lombok.NonNull;

import java.util.List;
import java.util.Map;

public class MateriaBusiness implements MateriaBusinessInterface {
    private static MateriaData materiaData = new MateriaData();
    @Override
    public int crearMateria(@NonNull Materia materia) {
        return materiaData.crearMateria(materia);
    }

    @Override
    public void borrarMateria(int idMateria) {
        materiaData.borrarMateria(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, Materia materia) {
        materiaData.editarMateria(idMateria, materia);
    }

    @Override
    public Materia buscarMateriaById(int idMateria) {
        return materiaData.buscarMateriaById(idMateria);
    }

    @Override
    public Materia buscarMateriaByNombre(String nombreMateria) {
        return materiaData.buscarMateriabyNombre(nombreMateria);
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() {
        return materiaData.obtenerListaMaterias();
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy order) {
        return materiaData.obtenerListaMateriasOrderedBy(order);
    }

}
