package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Interfaces.MateriaDataInterface;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.*;

import lombok.NonNull;
import org.springframework.stereotype.Service;

@Service
public class MateriaData  implements MateriaDataInterface{
    public static HashMap<Integer, Materia> listaMaterias = new HashMap<>();
    private static int contadorIds;

    public static enum OrderMateriaBy{
        nombre_asc,
        nombre_desc,
        codigo_asc,
        codigo_desc
    }

    @Override
    public int crearMateria(@NonNull Materia materia) {
        int idMateria = contadorIds++;
        listaMaterias.put(idMateria,materia);
        return idMateria;
    }

    @Override
    public void borrarMateria(int idMateria) {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        listaMaterias.remove(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, Materia materia) {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        listaMaterias.replace(idMateria, materia);
    }

    @Override
    public Materia buscarMateriaById(int idMateria) {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!listaMaterias.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        return listaMaterias.get(idMateria);
    }

    @Override
    public Materia buscarMateriabyNombre(String nombreMateria) {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        for (Materia materia: listaMaterias.values()) {
            if (materia.getNombre().equals(nombreMateria))
                return materia;
        }
        throw new MateriaNoEncontradaException();
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        return listaMaterias;
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(OrderMateriaBy order) {
        if (listaMaterias.isEmpty()) {
            throw new NoHayMateriasException();
        }
        List<Map.Entry<Integer, Materia>> listaMateriasOrdenada = new ArrayList<>(listaMaterias.entrySet());
        // Comparador para ordenar por nombre y, en caso de empate, por código (ID del mapa)
        Comparator<Map.Entry<Integer, Materia>> comparador = Comparator
                .comparing((Map.Entry<Integer, Materia> entry) -> entry.getValue().getNombre())
                .thenComparing(Map.Entry::getKey);

        if (order == OrderMateriaBy.nombre_asc) {
            listaMateriasOrdenada.sort(comparador);
        } else if (order == OrderMateriaBy.nombre_desc) {
            listaMateriasOrdenada.sort(comparador.reversed());
        } else if (order == OrderMateriaBy.codigo_asc) {
            // Ordenar por código (ID del mapa) de forma ascendente
            listaMateriasOrdenada.sort(Map.Entry.comparingByKey());
        } else if (order == OrderMateriaBy.codigo_desc) {
            // Ordenar por código (ID del mapa) de forma descendente
            listaMateriasOrdenada.sort(Map.Entry.comparingByKey(Comparator.reverseOrder()));
        } else {
            // Manejar caso inválido de OrderMateriaBy (puedes lanzar una excepción o hacer algo apropiado)
            throw new IllegalArgumentException("Ordenamiento no válido: " + order);
        }

        return listaMateriasOrdenada;
    }

}
