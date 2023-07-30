package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Interfaces.MateriaDataInterface;
import com.canclini.finalLaboIII.Entity.Materia;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class MateriaData  implements MateriaDataInterface{
    protected static Map<Integer, Materia> listaMaterias = new HashMap<>();
    private static int contadorIds;

    @Override
    public int crearMateria(@NonNull Materia materia) {
        int idMateria = contadorIds++;
        listaMaterias.put(idMateria,materia);
        return idMateria;
    }

    @Override
    public void borrarMateria(int idMateria) {
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        listaMaterias.remove(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, Materia materia) {
        listaMaterias.replace(idMateria, materia);
    }

    @Override
    public Materia buscarMateriaById(int idMateria) {
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        return listaMaterias.get(idMateria);
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() {
        return listaMaterias;
    }
}
