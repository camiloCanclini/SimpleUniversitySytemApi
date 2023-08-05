package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.AlumnoDto;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface AlumnoBusinessInterface {
    public int crearAlumno(AlumnoDto alumno);
    public void borrarAlumno(int idAlumon);
    public void editarAlumno(int idAlumno, AlumnoDto alumno);
    public Alumno buscarAlumnoById(int idAlumno);
    public Map<Integer, Alumno> obtenerListaAlumnos();
    public int aniadirAsignatura(int idAlumno, Asignatura asignatura);
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado);
}
