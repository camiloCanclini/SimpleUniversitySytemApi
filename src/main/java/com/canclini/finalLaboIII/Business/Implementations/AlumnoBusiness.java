package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Interfaces.AlumnoBusinessInterface;
import com.canclini.finalLaboIII.Data.Implementations.AlumnoData;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AlumnoBusiness implements AlumnoBusinessInterface {
    AlumnoData alumnoData = new AlumnoData();

    @Override
    public int crearAlumno(AlumnoDto alumnodto){
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnodto.getNombre());
        alumno.setApellido(alumnodto.getApellido());
        alumno.setDni(alumnodto.getDni());
        alumno.setAsignaturas(new HashMap<>());
        return alumnoData.crearAlumno(alumno);
    }

    @Override
    public void borrarAlumno(int idAlumno) {
        alumnoData.borrarAlumno(idAlumno);
    }

    @Override
    public void editarAlumno(int idAlumno, AlumnoDto alumnodto) {
        Alumno alumno = new Alumno();
        alumno.setNombre(alumnodto.getNombre());
        alumno.setApellido(alumnodto.getApellido());
        alumno.setDni(alumnodto.getDni());
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
    public int aniadirAsignatura(int idAlumno, AsignaturaDto asignaturaDto) {
        Asignatura asignatura = new Asignatura(asignaturaDto.getIdMateria(),asignaturaDto.getEstado(), asignaturaDto.getNota());
        return alumnoData.aniadirAsignatura(idAlumno, asignatura);
    }

    @Override
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, Asignatura.Estado estado) {
        alumnoData.cambiarEstadoAsignatura(idAlumno, idAsignatura, estado);
    }
}
