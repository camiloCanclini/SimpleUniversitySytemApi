package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.MateriaDto;
import com.canclini.finalLaboIII.Business.Interfaces.MateriaBusinessInterface;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class MateriaBusiness implements MateriaBusinessInterface {
    @Autowired
    private static MateriaData materiaData;

    @Override
    public int crearMateria(@NonNull MateriaDto materia) {
        return materiaData.crearMateria(new Materia(materia.getNombre(), materia.getAnio(), materia.getCuatrimestre(), null, new ArrayList<Materia>()));
    }

    @Override
    public void borrarMateria(int idMateria) {
        materiaData.borrarMateria(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, MateriaDto materia) {
        materiaData.editarMateria(idMateria, new Materia(materia.getNombre(), materia.getAnio(), materia.getCuatrimestre(), null, new ArrayList<Materia>()));
    }

    @Override
    public Materia buscarMateriaById(int idMateria) {
        return materiaData.buscarMateriaById(idMateria);
    }

    @Override
    public Materia buscarMateriaByNombre(String nombreMateria)  {
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
