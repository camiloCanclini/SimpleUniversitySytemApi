package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Business.Interfaces.AlumnoBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.EstadoAsignaturaNoPermitidoException;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Implementations.AlumnoData;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
@Service
public class AlumnoBusiness implements AlumnoBusinessInterface {
    @Autowired
    AlumnoData alumnoData;

    Map<Integer, Materia> listaMaterias = MateriaData.listaMaterias;
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
    public void editarAlumno(int idAlumno, AlumnoEditarDto alumnodto) {
        Alumno alumno = alumnoData.buscarAlumnoById(idAlumno);
        if (!(alumnodto.getNombre() == null)) {
            alumno.setNombre(alumnodto.getNombre());
        }
        if (!(alumnodto.getApellido() == null)) {
            alumno.setApellido(alumnodto.getApellido());
        }
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
        if (!listaMaterias.containsKey(asignaturaDto.getIdMateria())) {
            throw new MateriaNoEncontradaException();
        }
        Asignatura asignatura = new Asignatura(asignaturaDto.getIdMateria(), Asignatura.Estado.NO_CURSADA, null);
        return alumnoData.aniadirAsignatura(idAlumno, asignatura);
    }

    @Override
    public void cambiarEstadoAsignatura(int idAlumno, int idAsignatura, AsignaturaEditarDto asignaturaEditarDto) throws EstadoAsignaturaNoPermitidoException {
        Asignatura.Estado estado;
        try {
           estado = Asignatura.Estado.valueOf(asignaturaEditarDto.getEstado());
        }catch(IllegalArgumentException e){
            throw new EstadoAsignaturaNoPermitidoException();
        }
        alumnoData.cambiarEstadoAsignatura(idAlumno, idAsignatura, estado, asignaturaEditarDto.getNota());
    }
}
