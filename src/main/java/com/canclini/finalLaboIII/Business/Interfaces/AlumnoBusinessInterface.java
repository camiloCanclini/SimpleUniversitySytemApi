package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;

import java.util.Map;

public interface AlumnoBusinessInterface {
    public int crearAlumno(AlumnoDto alumno);
    public void borrarAlumno(int idAlumon) throws AlumnoNoEncontradoException;
    public void editarAlumno(int idAlumno, AlumnoEditarDto alumno) throws AlumnoNoEncontradoException;
    public Alumno buscarAlumnoById(int idAlumno) throws AlumnoNoEncontradoException;
    public Map<Integer, Alumno> obtenerListaAlumnos() throws NoHayAlumnosException;
    public int aniadirAsignatura(int idAlumno, AsignaturaDto asignatura) throws MateriaNoEncontradaException, AlumnoNoEncontradoException, NoHayMateriasException;
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, AsignaturaEditarDto asignaturaEditarDto) throws EstadoAsignaturaNoPermitidoException, AsignaturaNoEncontradaException, AlumnoNoEncontradoException;
}
