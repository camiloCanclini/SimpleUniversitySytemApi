package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.DepartamentoDto;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.HashMap;
import java.util.HashSet;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class DepartamentoBusinessTest {
    @Spy
    DepartamentoData departamentoData;

    @InjectMocks
    DepartamentoBusiness departamentoBusiness;

    @BeforeEach
    void setUp() throws NoHayDepartamentosException {
        MockitoAnnotations.openMocks(this);

        HashMap<Integer, String> listaDepas = new HashMap<>();
        listaDepas.put(0, "Departamento Periodistico");
        listaDepas.put(1, "Departamento Comunicacion");

        doReturn(listaDepas).when(departamentoData).obtenerListaDepartamentos();
    }
    @Test
    void crearDepartamento() throws NoHayDepartamentosException {

        DepartamentoDto dto = new DepartamentoDto();
        dto.setNombre("Departamento Electronica");
        DepartamentoDto dto2 = new DepartamentoDto();
        dto2.setNombre("Departamento Computacion");

        Integer result = departamentoBusiness.crearDepartamento(dto);
        Integer result2 = departamentoBusiness.crearDepartamento(dto2);

        assertNotNull(result); // Verificar que el resultado no sea nulo
        assertNotNull(result2); // Verificar que el resultado no sea nulo
        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
        assertTrue(result2 > result);

        assertDoesNotThrow(()->{
            departamentoBusiness.crearDepartamento(dto);
            departamentoBusiness.crearDepartamento(dto2);
        });
    }

    @Test
    void borrarDepartamento() {
        assertDoesNotThrow(()->{
            departamentoBusiness.borrarDepartamento(1);
        });

        assertThrows(DepartamentoNoEncontradoException.class,()->{
            departamentoBusiness.borrarDepartamento(5);
        });
    }

    /*
    @Test
    void obtenerListaDepartamentos() {
    }
    */

}