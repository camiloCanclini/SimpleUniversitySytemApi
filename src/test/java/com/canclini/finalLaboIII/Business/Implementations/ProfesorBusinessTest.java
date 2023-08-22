package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Data.Implementations.ProfesorData;
import com.canclini.finalLaboIII.Entity.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class ProfesorBusinessTest {
    @Mock
    MateriaData materiaData;
    @Spy
    ProfesorData profesorData;

    @InjectMocks
    ProfesorBusiness profesorBusiness;

    @BeforeEach
    void setUp() throws NoHayMateriasException, NoHayProfesoresException {

        MockitoAnnotations.openMocks(this);
        Profesor profe = new Profesor();
        profe.setNombre("Pepe");
        profe.setApellido("Argento");
        profe.setDni(44453822L);
        profe.setTitulo("Licenciado");

        Profesor profe2 = new Profesor();
        profe2.setNombre("Monica");
        profe2.setApellido("Argento");
        profe2.setDni(43423422L);
        profe2.setTitulo("");

        HashMap<Integer, Profesor> listaProfes = new HashMap<>();
        listaProfes.put(0, profe);
        listaProfes.put(1, profe2);

        doReturn(listaProfes).when(profesorData).obtenerListaProfesor();

    }

    @Test
    void crearProfesor() {

        ProfesorDto dto = new ProfesorDto();
        dto.setNombre("Pepe");
        dto.setApellido("Argento");
        dto.setDni(44453822L);
        dto.setTitulo("Licenciado");

        when(profesorData.crearProfesor(any(Profesor.class))).thenReturn(2);

        Integer result = profesorBusiness.crearProfesor(dto);
        System.out.println("Result: " + result); // Mostrar el valor de result en la consola

        assertNotNull(result); // Verificar que el resultado no sea nulo
        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
    }

    @Test
    void borrarProfesor() throws ProfesorNoEncontradoException {

        assertDoesNotThrow(()->{
            profesorBusiness.borrarProfesor(1);
        });

        assertThrows(ProfesorNoEncontradoException.class,()->{
            profesorBusiness.borrarProfesor(5);
        });

    }

    /*@Test
    void agregarMateria() throws MateriaNoEncontradaException, ProfesorNoEncontradoException, NoHayMateriasException, NoHayProfesoresException {
    }*/

    /*@Test
    void borrarMateria() {
    }*/

    @Test
    void buscarProfesorById() throws ProfesorNoEncontradoException, NoHayProfesoresException {

        assertDoesNotThrow(()->{
            Profesor profesor = profesorData.buscarProfesorById(1);
        });
        Profesor profesor = profesorData.buscarProfesorById(1);
        assertNotNull(profesor);
    }

    /*@Test
    void obtenerListaProfesor() {

    }*/
}