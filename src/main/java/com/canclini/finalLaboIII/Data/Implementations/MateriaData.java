package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
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
    public void borrarMateria(int idMateria) throws NoHayMateriasException, MateriaNoEncontradaException {
        if (obtenerListaMaterias().isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        obtenerListaMaterias().remove(idMateria);
    }

    @Override
    public void editarMateria(int idMateria, Materia materia) throws NoHayMateriasException, MateriaNoEncontradaException {
        if (obtenerListaMaterias().isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        obtenerListaMaterias().replace(idMateria, materia);
    }

    @Override
    public Materia buscarMateriaById(int idMateria) throws NoHayMateriasException, MateriaNoEncontradaException {
        if (obtenerListaMaterias().isEmpty()) {
            throw new NoHayMateriasException();
        }
        if (!obtenerListaMaterias().containsKey(idMateria)) {
            throw new MateriaNoEncontradaException();
        }
        return obtenerListaMaterias().get(idMateria);
    }

    @Override
    public Materia buscarMateriabyNombre(String nombreMateria) throws NoHayMateriasException, MateriaNoEncontradaException {
        if (obtenerListaMaterias().isEmpty()) {
            throw new NoHayMateriasException();
        }
        for (Materia materia: obtenerListaMaterias().values()) {
            if (materia.getNombre().equals(nombreMateria))
                return materia;
        }
        throw new MateriaNoEncontradaException();
    }

    @Override
    public Map<Integer, Materia> obtenerListaMaterias() throws NoHayMateriasException {
        if (lista.isEmpty()) {
            throw new NoHayMateriasException();
        }
        return lista;
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriasOrderedBy(OrderMateriaBy order) throws NoHayMateriasException, IllegalArgumentException {
        if (obtenerListaMaterias().isEmpty()) {
            throw new NoHayMateriasException();
        }
        List<Map.Entry<Integer, Materia>> listaMateriasOrdenada = new ArrayList<>(obtenerListaMaterias().entrySet());
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
    public List<Map.Entry<Integer, Materia>> obtenerMateriasDictadasPorProfesor(Integer idProfesor) throws NoHayMateriasException {
        ArrayList<Map.Entry<Integer, Materia>> materiasQueDictaProfesor = new ArrayList<>();
        for (Map.Entry<Integer, Materia> materia: obtenerListaMaterias().entrySet()) {
            if (materia.getValue().getProfesores().contains(idProfesor)) {
                materiasQueDictaProfesor.add(materia);
            }
        }
        return materiasQueDictaProfesor;
    }

    public Set<Integer> obtenerIdsMateriasDictadasPorProfesor(Integer idProfesor) throws NoHayMateriasException {
        HashSet<Integer> materiasQueDictaProfesor = new HashSet<>();
        for (Map.Entry<Integer, Materia> materia: obtenerListaMaterias().entrySet()) {
            if (materia.getValue().getProfesores().contains(idProfesor)) {
                materiasQueDictaProfesor.add(materia.getKey());
            }
        }
        return materiasQueDictaProfesor;
    }
    @Override
    public void agregarProfesorAMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, NoHayMateriasException, NoHayProfesoresException {
        buscarMateriaById(idMateria).getProfesores().add(idProfesor);
    }
    @Override
    public void sacarProfesorDeMateria(int idProfesor, Integer idMateria) throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException, NoHayProfesoresException {
        buscarMateriaById(idMateria).getProfesores().remove(idProfesor);
    }
}
