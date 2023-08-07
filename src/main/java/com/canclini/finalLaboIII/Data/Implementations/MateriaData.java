package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Interfaces.MateriaDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.*;

import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
@Slf4j
@Repository
public class MateriaData extends MemoryDataAbstract<Materia> implements MateriaDataInterface{

    public enum OrderMateriaBy{
        nombre_asc,
        nombre_desc,
        codigo_asc,
        codigo_desc
    }

    @Override
    public int crearMateria(@NonNull Materia materia) {
        log.info(String.valueOf(materia));
        int idMateria = generarId();
        lista.put(idMateria,materia);
        return idMateria;
    }

    @Override
    public void borrarMateria(int idMateria) {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!lista.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        lista.remove(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, Materia materia) {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!lista.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        lista.replace(idMateria, materia);
    }

    @Override
    public Materia buscarMateriaById(int idMateria) {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!lista.containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        return lista.get(idMateria);
    }

    @Override
    public Materia buscarMateriabyNombre(String nombreMateria) {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        for (Materia materia: lista.values()) {
            if (materia.getNombre().equals(nombreMateria))
                return materia;
        }
        throw new MateriaNoEncontradaException();
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        return lista;
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(OrderMateriaBy order) {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        List<Map.Entry<Integer, Materia>> listaMateriasOrdenada = new ArrayList<>(lista.entrySet());
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
