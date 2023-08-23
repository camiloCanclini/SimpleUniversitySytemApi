package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.AsignaturaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayAlumnosException;
import com.canclini.finalLaboIII.Data.Interfaces.AlumnoDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import lombok.NonNull;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Repository
public class AlumnoData extends MemoryDataAbstract<Alumno> implements AlumnoDataInterface {

    @Override
    public int crearAlumno(@NonNull Alumno alumno){
        int idAlumno = generarId();
        lista.put(idAlumno, alumno);
        return idAlumno;
    }

    @Override
    public void borrarAlumno(int idAlumno) throws AlumnoNoEncontradoException, NoHayAlumnosException {
        if (!obtenerListaAlumnos().containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException( );
        }
        obtenerListaAlumnos().remove(idAlumno);
    }

    @Override
    public void editarAlumno(int idAlumno, @NonNull Alumno alumno) throws AlumnoNoEncontradoException, NoHayAlumnosException {
        if (!obtenerListaAlumnos().containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        obtenerListaAlumnos().replace(idAlumno,alumno);
    }

    @Override
    public Alumno buscarAlumnoById(int idAlumno) throws AlumnoNoEncontradoException, NoHayAlumnosException {
        if (!obtenerListaAlumnos().containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        return obtenerListaAlumnos().get(idAlumno);
    }

    @Override
    public Map<Integer, Alumno> obtenerListaAlumnos() throws NoHayAlumnosException {
        if (lista.isEmpty()) {
            throw new NoHayAlumnosException();
        }

        return lista;
    }


    @Override
    public int aniadirAsignatura(int idAlumno, Asignatura asignatura) throws AlumnoNoEncontradoException, NoHayAlumnosException {
        if (!obtenerListaAlumnos().containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }

        Alumno alumno = obtenerListaAlumnos().get(idAlumno);
        alumno.getAsignaturas().put(asignatura.getIdMateria(), asignatura);
        return asignatura.getIdMateria();
    }

    @Override
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado, Integer nota) throws AlumnoNoEncontradoException, AsignaturaNoEncontradaException, NoHayAlumnosException {
        if (!obtenerListaAlumnos().containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        if (!obtenerListaAlumnos().get(idAlumno).getAsignaturas().containsKey(idAsignatura)){
            throw new AsignaturaNoEncontradaException();
        }
        HashMap<Integer, Asignatura> asignaturas = obtenerListaAlumnos().get(idAlumno).getAsignaturas();
        asignaturas.get(idAsignatura).setEstado(estado);
    }


}
