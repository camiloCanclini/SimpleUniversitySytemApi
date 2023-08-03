package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.AsignaturaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayAlumnosException;
import com.canclini.finalLaboIII.Data.Interfaces.AlumnoDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.Map;

public class AlumnoData extends MemoryDataAbstract<Alumno> implements AlumnoDataInterface {
    @Override
    public int crearAlumno(@NonNull Alumno alumno) {
        int idAlumno = generarId();
        lista.put(idAlumno, alumno);
        return idAlumno;
    }

    @Override
    public void borrarAlumno(int idAlumno){
        if (!lista.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException( );
        }
        lista.remove(idAlumno);
    }

    @Override
    public void editarAlumno(int idAlumno, @NonNull Alumno alumno) {
        if (!lista.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        lista.replace(idAlumno,alumno);
    }

    @Override
    public Alumno buscarAlumnoById(int idAlumno) {
        if (!lista.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        return lista.get(idAlumno);
    }

    @Override
    public Map<Integer, Alumno> obtenerListaAlumnos() {
        if (lista.isEmpty()) {
            throw new NoHayAlumnosException();
        }

        return lista;
    }


    @Override
    public int aniadirAsignatura(int idAlumno, Asignatura asignatura) {
        if (!lista.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        Alumno alumno = lista.get(idAlumno);
        alumno.getAsignaturas().put(asignatura.getIdMateria(), asignatura);
        return asignatura.getIdMateria();
    }

    @Override
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado) {
        if (!lista.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        if (!lista.get(idAlumno).getAsignaturas().containsKey(idAsignatura)){
            throw new AsignaturaNoEncontradaException();
        }
        HashMap<Integer, Asignatura> asignaturas = lista.get(idAlumno).getAsignaturas();
        asignaturas.get(idAsignatura).setEstado(estado);
    }


}
