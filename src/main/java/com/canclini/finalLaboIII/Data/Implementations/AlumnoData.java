package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Interfaces.AlumnoDataInterface;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Materia;
import lombok.NonNull;

import java.util.HashMap;
import java.util.Map;

public class AlumnoData implements AlumnoDataInterface {
    protected static Map<Integer, Alumno> listaAlumnos = new HashMap<>();
    private static int contadorIds = 0;
    @Override
    public int crearAlumno(@NonNull Alumno alumno) {
        int idAlumno = contadorIds++;
        listaAlumnos.put(idAlumno, alumno);
        return idAlumno;
    }

    @Override
    public void borrarAlumno(int idAlumno){
        if (!listaAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        listaAlumnos.remove(idAlumno);
    }

    @Override
    public void editarAlumno(int idAlumno, @NonNull Alumno alumno) {
        if (!listaAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        listaAlumnos.replace(idAlumno,alumno);
    }

    @Override
    public Alumno buscarAlumnoById(int idAlumno) {
        if (!listaAlumnos.containsKey(idAlumno)) {
            throw new AlumnoNoEncontradoException();
        }
        return listaAlumnos.get(idAlumno);
    }

    @Override
    public Map<Integer, Alumno> obtenerListaAlumnos() {
        return listaAlumnos;
    }


}
