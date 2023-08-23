package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Implementations.CarreraData;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import com.canclini.finalLaboIII.Entity.Carrera;
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

class CarreraBusinessTest {
    @Spy
    CarreraData carreraData;

    @Mock
    DepartamentoData departamentoData;

    @InjectMocks
    CarreraBusiness carreraBusiness;
    @BeforeEach
    void setUp() throws NoHayCarrerasException, NoHayDepartamentosException {
        MockitoAnnotations.openMocks(this);

        Carrera carrera = new Carrera();
        carrera.setNombre("Tecnicatura Universitaria en Programacion");
        carrera.setCantidadAnios(2);
        carrera.setDepartamentoId(2);
        carrera.setMateriasList(new HashSet<>(){{
            add(0);
            add(2);
            add(9);
            add(7);
        }});

        Carrera carrera2 = new Carrera();
        carrera2.setNombre("Ingenieria Informatica");
        carrera2.setCantidadAnios(5);
        carrera2.setDepartamentoId(2);
        carrera2.setMateriasList(new HashSet<>());


        HashMap<Integer, Carrera> listaCarrera = new HashMap<>();
        listaCarrera.put(0, carrera);
        listaCarrera.put(1, carrera2);

        doReturn(listaCarrera).when(carreraData).obtenerListaCarrera();

        HashMap<Integer, String> listaDepas = new HashMap<>();
        listaDepas.put(0, "Departamento Periodistico");
        listaDepas.put(1, "Departamento Comunicacion");
        listaDepas.put(2, "Departamento Informatica");
        listaDepas.put(3, "Departamento Inteligencia");

        doReturn(listaDepas).when(departamentoData).obtenerListaDepartamentos();
    }

    @Test
    void crearCarrera() throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        CarreraDto dto = new CarreraDto();
        dto.setNombre("Ingenieria En Inteligencia Artificial");
        dto.setCantidadAnios(3);
        dto.setDepartamentoId(3);

        assertDoesNotThrow(()->{
            carreraBusiness.crearCarrera(dto);
        });

        dto.setDepartamentoId(8);

        assertThrows(DepartamentoNoEncontradoException.class, ()->{
            carreraBusiness.crearCarrera(dto);
        });

        dto.setDepartamentoId(3);

        int result = carreraBusiness.crearCarrera(dto);
        System.out.println("Result: " + result); // Mostrar el valor de result en la consola

        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
    }

    @Test
    void borrarCarrera() {

        assertDoesNotThrow(()->{
            carreraBusiness.borrarCarrera(1);
        });

        assertThrows(CarreraNoEncontradaException.class,()->{
            carreraBusiness.borrarCarrera(5);
        });
    }

    @Test
    void editarCarrera() throws NoHayCarrerasException, NoHayDepartamentosException, DepartamentoNoEncontradoException, CarreraNoEncontradaException {
        String nuevoNombre = "TUP";
        Integer nuevoAnios = 3;

        CarreraDto dto = new CarreraDto();
        dto.setNombre(nuevoNombre);
        dto.setCantidadAnios(nuevoAnios);
        dto.setDepartamentoId(2);// no se modifica

        String nombreCarreraAntes = carreraBusiness.obtenerListaCarrera().get(0).getNombre();
        Integer depaCarreraAntes = carreraBusiness.obtenerListaCarrera().get(0).getDepartamentoId();
        Integer aniosCarreraAntes = carreraBusiness.obtenerListaCarrera().get(0).getCantidadAnios();

        carreraBusiness.editarCarrera(0, dto);

        String nombreCarreraDespues = carreraBusiness.obtenerListaCarrera().get(0).getNombre();
        Integer depaCarreraDespues = carreraBusiness.obtenerListaCarrera().get(0).getDepartamentoId();
        Integer aniosCarreraDespues = carreraBusiness.obtenerListaCarrera().get(0).getCantidadAnios();

        assertEquals(depaCarreraAntes, depaCarreraDespues);// NO se modifico

        assertEquals(nombreCarreraDespues, nuevoNombre);
        assertEquals(aniosCarreraDespues, nuevoAnios);

        assertNotEquals(nombreCarreraAntes, nombreCarreraDespues);
        assertNotEquals(aniosCarreraAntes, aniosCarreraDespues);

    }

    @Test
    void buscarCarreraById() {
        assertDoesNotThrow(()->{
            carreraBusiness.buscarCarreraById(0);
        });
        assertThrows(CarreraNoEncontradaException.class, ()->{
            carreraBusiness.buscarCarreraById(4);
        });
    }
    @Test
    void obtenerListaMateriaDeCarrera() throws NoHayCarrerasException, CarreraNoEncontradaException {
        assertDoesNotThrow(()->{
            carreraBusiness.obtenerListaMateriaDeCarrera(0);
        });
        assertThrows(CarreraNoEncontradaException.class, ()->{
            carreraBusiness.obtenerListaMateriaDeCarrera(4);
        });
        HashSet<Integer> listaMaterias = carreraBusiness.obtenerListaMateriaDeCarrera(0);
        assertNotNull(listaMaterias);
    }
    /*
    @Test
    void obtenerListaCarrera() {
    }
    */

}