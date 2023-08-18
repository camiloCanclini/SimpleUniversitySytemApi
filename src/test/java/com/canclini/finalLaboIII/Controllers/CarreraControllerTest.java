package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Entity.Carrera;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentMatchers;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.AdditionalMatchers.lt;
import static org.mockito.Mockito.*;

class CarreraControllerTest {

    @Mock // Se simula la dependencia de la clase que estamos testeando, en este caso puedo
    private CarreraBusiness carreraBusiness;

    @InjectMocks // Se inyecta lo anterior en la clase que se va a testear
    private CarreraController carreraController;

    MockMvc mockMvc;
    @BeforeEach // Se inicia el testeo
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test // Metodo a testear
    void testGetCarreraById_WithValidId() throws NoHayCarrerasException, CarreraNoEncontradaException {
        // Simulamos el comportamiento de carreraBusiness
        Carrera carrera = new Carrera();
        // Se configura el metodo que se esta testeando,y se especifica que debe devolver
        // when(carreraBusiness.buscarCarreraById(anyInt())): Se dice que metodo se va a usar y que argumento puede recibir
        // .thenReturn(carrera);: Nos dice que va a devolver devolver el metodo
        when(carreraBusiness.buscarCarreraById(lt(0)))// lt: lessthan
                .thenThrow(new CarreraNoEncontradaException());
        when(carreraBusiness.buscarCarreraById(anyInt()))
                .thenReturn(carrera);

        ResponseEntity<ResponseDtoJson> response = carreraController.getCarreraById(1);
        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertNotNull(response.getBody());
        assertTrue(response.getBody().data instanceof Carrera);


        ResponseEntity<ResponseDtoJson> response2 = carreraController.getCarreraById(-5);
        assertEquals(HttpStatus.BAD_REQUEST, response2.getStatusCode());
    }

    @Test
    void testGetCarreraById_NoHayCarrerasException() throws NoHayCarrerasException, CarreraNoEncontradaException {
        // Simulamos el comportamiento de carreraBusiness
        when(carreraBusiness.buscarCarreraById(anyInt())).thenThrow(NoHayCarrerasException.class);

        ResponseEntity<ResponseDtoJson> response = carreraController.getCarreraById(1);
        ResponseEntity<ResponseDtoJson> response2 = carreraController.getCarreraById(-5);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    @Test
    void testGetCarreras() throws NoHayCarrerasException {
        // Configuramos el comportamiento para cuando se llame a crearCarrera con un argumento nulo
        when(carreraController.crearCarrera(eq(null)))
                .thenReturn(ResponseEntity.status(HttpStatus.BAD_REQUEST).build());

        ResponseEntity<ResponseDtoJson> response = carreraController.crearCarrera(null);

        assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
        assertNotNull(response.getBody());
    }

    // Aquí puedes agregar más pruebas para los otros métodos de CarreraController
}
