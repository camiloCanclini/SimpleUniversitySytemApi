package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Interfaces.AlumnoBusinessInterface;
import com.canclini.finalLaboIII.Data.Implementations.AlumnoData;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public class AlumnoBusiness implements AlumnoBusinessInterface {
    AlumnoData alumnoData = new AlumnoData();

    @Override
    public int crearAlumno(Alumno alumno) {
        return alumnoData.crearAlumno(alumno);
    }

    @Override
    public void borrarAlumno(int idAlumno) {
        alumnoData.borrarAlumno(idAlumno);
    }

    @Override
    public void editarAlumno(int idAlumno, Alumno alumno) {
        alumnoData.editarAlumno(idAlumno, alumno);
    }

    @Override
    public Alumno buscarAlumnoById(int idAlumno) {
        return alumnoData.buscarAlumnoById(idAlumno);
    }

    @Override
    public Map<Integer, Alumno> obtenerListaAlumnos() {
        return alumnoData.obtenerListaAlumnos();
    }

    @Override
    public int aniadirAsignatura(int idAlumno, Asignatura asignatura) {
        return alumnoData.aniadirAsignatura(idAlumno, asignatura);
    }

    @Override
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado) {
        alumnoData.cambiarEstadoAsignatura(idAlumno, idAsignatura, estado);
    }
}
