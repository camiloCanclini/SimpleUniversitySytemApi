package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Business.Implementations.DepartamentoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Profesor;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.HashSet;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class CarreraControllerTest {

    MockMvc mockMvc;
    @Spy // Se simula la dependencia de la clase que estamos testeando, en este caso puedo
    private CarreraBusiness carreraBusiness;

    @Spy // Se simula la dependencia de la clase que estamos testeando, en este caso puedo
    private DepartamentoBusiness departamentoBusiness;

    @InjectMocks // Se inyecta lo anterior en la clase que se va a testear
    private CarreraController carreraController;

    @BeforeEach
    void setUp() throws NoHayCarrerasException, NoHayDepartamentosException {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(carreraController).build();

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

        doReturn(listaCarrera).when(carreraBusiness).obtenerListaCarrera();

        HashMap<Integer, String> listaDepas = new HashMap<>();
        listaDepas.put(0, "Departamento Periodistico");
        listaDepas.put(1, "Departamento Comunicacion");
        listaDepas.put(2, "Departamento Informatica");
        listaDepas.put(3, "Departamento Inteligencia");

        doReturn(listaDepas).when(departamentoBusiness).obtenerListaDepartamentos();
    }

    @Test
    void getCarreraById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carrera/1"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists()); // Cambiar el valor esperado según tu lógica

        //Mockito.verify(carreraBusiness, Mockito.times(1)).crearCarrera(Mockito.any(CarreraDto.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/carrera/5"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica

        doThrow(new NoHayCarrerasException()).when(carreraBusiness).obtenerListaCarrera();
        mockMvc.perform(MockMvcRequestBuilders.get("/carreras"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NO_CONTENT"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
    }

    @Test
    void getCarreras() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/carreras"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isMap()); // Cambiar el valor esperado según tu lógica
        doThrow(new NoHayCarrerasException()).when(carreraBusiness).obtenerListaCarrera();
        mockMvc.perform(MockMvcRequestBuilders.get("/carreras"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NO_CONTENT"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
    }

    @Test
    void crearCarrera() throws Exception {
        doReturn(0).when(carreraBusiness).crearCarrera(any(CarreraDto.class));

        CarreraDto dto = new CarreraDto();
        dto.setNombre("TUP");
        dto.setCantidadAnios(2);
        dto.setDepartamentoId(2);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica

        Mockito.verify(carreraBusiness, Mockito.times(1)).crearCarrera(Mockito.any(CarreraDto.class));

        CarreraDto dto2 = new CarreraDto();
        dto2.setNombre("TUP");
        dto2.setCantidadAnios(0);
        dto2.setDepartamentoId(2);

        ObjectMapper obj2 = new ObjectMapper();
        String json2 = obj2.writeValueAsString(dto2);

        mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType("application/json")
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        CarreraDto dto3 = new CarreraDto();
        dto3.setNombre("TUP");
        dto3.setCantidadAnios(1);
        dto3.setDepartamentoId(8);

        ObjectMapper obj3 = new ObjectMapper();
        String json3 = obj3.writeValueAsString(dto3);

        mockMvc.perform(MockMvcRequestBuilders.post("/carrera")
                        .contentType("application/json")
                        .content(json3))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void editarCarrera() throws Exception {
        doNothing().when(carreraBusiness).editarCarrera(anyInt(),any(CarreraDto.class));

        CarreraDto dto = new CarreraDto();
        dto.setNombre("TUP");
        dto.setCantidadAnios(4);
        dto.setDepartamentoId(2);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        /*
        ObjectMapper obj2 = new ObjectMapper();
        String json2 = obj2.writeValueAsString(null);
        */

        mockMvc.perform(MockMvcRequestBuilders.put("/carrera/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        doThrow(new CarreraNoEncontradaException()).when(carreraBusiness).editarCarrera(anyInt(),any(CarreraDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/carrera/4")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void eliminarCarrera() throws Exception {
        doNothing().when(carreraBusiness).borrarCarrera(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carrera/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        doThrow(new CarreraNoEncontradaException()).when(carreraBusiness).borrarCarrera(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carrera/4")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        doThrow(new NoHayCarrerasException()).when(carreraBusiness).borrarCarrera(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/carrera/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    // Aquí puedes agregar más pruebas para los otros métodos de CarreraController
}
