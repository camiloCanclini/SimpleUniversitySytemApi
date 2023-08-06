package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Data.Exceptions.EstadoAsignaturaNoPermitidoException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;

import java.util.Map;

public interface AlumnoBusinessInterface {
    public int crearAlumno(AlumnoDto alumno);
    public void borrarAlumno(int idAlumon);
    public void editarAlumno(int idAlumno, AlumnoEditarDto alumno);
    public Alumno buscarAlumnoById(int idAlumno);
    public Map<Integer, Alumno> obtenerListaAlumnos();
    public int aniadirAsignatura(int idAlumno, AsignaturaDto asignatura);
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, AsignaturaEditarDto asignaturaEditarDto) throws EstadoAsignaturaNoPermitidoException;
}
