package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Data.Implementations.AlumnoData;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Materia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class AlumnoBusinessTest {
    @Spy
    AlumnoData alumnoData;

    @Mock
    MateriaData materiaData;
    @InjectMocks
    AlumnoBusiness alumnoBusiness;
    @BeforeEach
    void setUp() throws NoHayMateriasException, NoHayAlumnosException {
        MockitoAnnotations.openMocks(this);


        Materia mat = new Materia();
        mat.setNombre("Matematica I");
        mat.setAnio(1);
        mat.setCuatrimestre(1);
        mat.setCorrelatividades(new HashSet<Integer>());

        Materia mat2 = new Materia();
        mat2.setNombre("Fisica I");
        mat2.setAnio(1);
        mat2.setCuatrimestre(2);
        mat2.setCorrelatividades(new HashSet<Integer>());

        Materia mat3 = new Materia();
        mat2.setNombre("Comunicacion I");
        mat2.setAnio(1);
        mat2.setCuatrimestre(1);
        mat2.setCorrelatividades(new HashSet<Integer>());

        HashMap<Integer, Materia> listaMaterias = new HashMap<>();
        listaMaterias.put(0, mat);
        listaMaterias.put(1, mat2);
        listaMaterias.put(2, mat3);

        doReturn(listaMaterias).when(materiaData).obtenerListaMaterias();

        Alumno alumno = new Alumno();
        alumno.setNombre("Pepe");
        alumno.setApellido("Argento");
        alumno.setDni(23123125L);
        alumno.setAsignaturas(new HashMap<>(){{
            put(0, new Asignatura(0, Asignatura.Estado.NO_CURSADA, null));
        }});

        Alumno alumno2 = new Alumno();
        alumno2.setNombre("Monica");
        alumno2.setApellido("Fernandez");
        alumno2.setDni(345466545L);
        alumno2.setAsignaturas(new HashMap<>(){{
            put(1, new Asignatura(0, Asignatura.Estado.NO_CURSADA, null));
            put(2, new Asignatura(0, Asignatura.Estado.NO_CURSADA, null));
        }});


        HashMap<Integer, Alumno> listaAlumnos = new HashMap<>();
        listaAlumnos.put(0, alumno);
        listaAlumnos.put(1, alumno2);

        doReturn(listaAlumnos).when(alumnoData).obtenerListaAlumnos();

    }

    @Test
    void crearAlumno() throws NoHayAlumnosException {
        AlumnoDto dto = new AlumnoDto();
        dto.setNombre("Camilo");
        dto.setApellido("Canclini");
        dto.setDni(87897878L);

        int result = alumnoBusiness.crearAlumno(dto);
        System.out.println("Result: " + result); // Mostrar el valor de result en la consola

        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
    }

    @Test
    void borrarAlumno() {
        assertDoesNotThrow(()->{
            alumnoBusiness.borrarAlumno(1);
        });

        assertThrows(AlumnoNoEncontradoException.class,()->{
            alumnoBusiness.borrarAlumno(5);
        });
    }

    @Test
    void editarAlumno() throws NoHayAlumnosException, AlumnoNoEncontradoException {

        String nuevoNombre = "Pepito";
        //String nuevoApellido = "Mendoza";
        AlumnoEditarDto dto = new AlumnoEditarDto();
        dto.setNombre(nuevoNombre); // Editada
        //dto.setApellido(nuevoApellido); // Editada

        Alumno alumnoAntes = alumnoBusiness.obtenerListaAlumnos().get(0);
        String nombreAlumnoAntes = alumnoAntes.getNombre();

        alumnoBusiness.editarAlumno(0, dto);

        Alumno alumnoDespues = alumnoBusiness.obtenerListaAlumnos().get(0);
        String nombreAlumnoDespues = alumnoAntes.getNombre();

        assertEquals(alumnoAntes.getApellido(), alumnoDespues.getApellido()); // NO se modifico

        assertEquals(alumnoDespues.getNombre(), nuevoNombre);

        assertNotEquals(nombreAlumnoAntes, nombreAlumnoDespues);
    }

    @Test
    void buscarAlumnoById() {
        assertDoesNotThrow(()->{
            alumnoBusiness.buscarAlumnoById(0);
        });
        assertThrows(AlumnoNoEncontradoException.class, ()->{
            alumnoBusiness.buscarAlumnoById(4);
        });
    }
    /*
    @Test
    void obtenerListaAlumnos() {

    }
    */
    @Test
    void aniadirAsignatura() {
        AsignaturaDto dto = new AsignaturaDto();
        dto.setIdMateria(1);

        assertDoesNotThrow(()->{
            alumnoBusiness.aniadirAsignatura(1, dto);
        });

        assertThrows(AlumnoNoEncontradoException.class,()->{
            alumnoBusiness.aniadirAsignatura(5, dto);
        });

        assertThrows(MateriaNoEncontradaException.class,()->{
            dto.setIdMateria(7);
            alumnoBusiness.aniadirAsignatura(1, dto);
        });


    }

    @Test
    void cambiarEstadoAsignatura() {
        AsignaturaEditarDto dto = new AsignaturaEditarDto();
        dto.setEstado("CURSADA");

        assertDoesNotThrow(()->{
            dto.setEstado("CURSADA");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
            dto.setEstado("APROBADA");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
            dto.setEstado("NO_CURSADA");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
            dto.setEstado("cursada");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
            dto.setEstado("aprobada");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
            dto.setEstado("no_cursada");
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
        });

        assertThrows(AlumnoNoEncontradoException.class,()->{
            alumnoBusiness.cambiarEstadoAsignatura(5,0, dto);
        });

        assertThrows(AsignaturaNoEncontradaException.class,()->{
            alumnoBusiness.cambiarEstadoAsignatura(1, 5,dto);
        });

        dto.setEstado("RecontraCursada");
        assertThrows(EstadoAsignaturaNoPermitidoException.class,()->{
            alumnoBusiness.cambiarEstadoAsignatura(0,0, dto);
        });
    }
}