package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.Map;

public interface AlumnoDataInterface {
    public int crearAlumno(Alumno alumno);
    public void borrarAlumno(int idAlumon);
    public void editarAlumno(int idAlumno, Alumno alumno);
    public Alumno buscarAlumnoById(int idAlumno);
    public Map<Integer, Alumno> obtenerListaAlumnos();
}
