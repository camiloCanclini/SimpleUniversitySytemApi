package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.AsignaturaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayAlumnosException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.Map;

public interface AlumnoDataInterface {
    public int crearAlumno(Alumno alumno);
    public void borrarAlumno(int idAlumon) throws AlumnoNoEncontradoException;
    public void editarAlumno(int idAlumno, Alumno alumno) throws AlumnoNoEncontradoException;
    public Alumno buscarAlumnoById(int idAlumno) throws AlumnoNoEncontradoException;
    public Map<Integer, Alumno> obtenerListaAlumnos() throws NoHayAlumnosException;
    public int aniadirAsignatura(int idAlumno, Asignatura asignatura) throws AlumnoNoEncontradoException;
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado, Integer nota) throws AlumnoNoEncontradoException, AsignaturaNoEncontradaException;


}
