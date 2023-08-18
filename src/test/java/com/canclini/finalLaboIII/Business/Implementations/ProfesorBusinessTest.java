package com.canclini.finalLaboIII.Business.Implementations;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Business.Implementations.ProfesorBusiness;
import com.canclini.finalLaboIII.Data.Implementations.ProfesorData;
import com.canclini.finalLaboIII.Entity.Profesor;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

class ProfesorBusinessTest {

    @Mock
    ProfesorData profesorData;

    @InjectMocks
    ProfesorBusiness profesorBusiness;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void crearProfesor() {
        ProfesorDto dto = new ProfesorDto();
        dto.setNombre("Pepe");
        dto.setApellido("Argento");
        dto.setDni(44453822L);
        dto.setTitulo("Licenciado");
        when(profesorData.crearProfesor(any(Profesor.class))).thenReturn(20);
        Integer result = profesorBusiness.crearProfesor(dto);
        System.out.println("Result: " + result); // Mostrar el valor de result en la consola

        assertNotNull(result); // Verificar que el resultado no sea nulo
        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
    }

    @Test
    void borrarProfesor() {
    }

    @Test
    void agregarMateria() {
    }

    @Test
    void borrarMateria() {
    }

    @Test
    void buscarProfesorById() {
    }

    @Test
    void obtenerListaProfesor() {
    }
}