package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Business.Implementations.DepartamentoBusiness;
import com.canclini.finalLaboIII.Business.Implementations.MateriaBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class MateriaControllerTest {
    MockMvc mockMvc;
    @Spy // Se simula la dependencia de la clase que estamos testeando, en este caso puedo
    private MateriaBusiness materiaBusiness;

    @InjectMocks // Se inyecta lo anterior en la clase que se va a testear
    private MateriaController materiaController;
    @BeforeEach
    void setUp() throws NoHayMateriasException, MateriaNoEncontradaException {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(materiaController).build();

        Materia mat = new Materia();
        mat.setNombre("Matematica I");
        mat.setAnio(1);
        mat.setCuatrimestre(1);
        mat.setProfesores(new HashSet<Integer>());
        mat.setCorrelatividades(new HashSet<Integer>());

        Materia mat2 = new Materia();
        mat2.setNombre("Fisica II");
        mat2.setAnio(1);
        mat2.setCuatrimestre(2);
        mat2.setProfesores(new HashSet<Integer>(){{
            add(2); // Se agrega el profesor con el ID:2
        }});
        mat2.setCorrelatividades(new HashSet<Integer>());

        HashMap<Integer, Materia> listaMaterias = new HashMap<>();
        listaMaterias.put(0, mat);
        listaMaterias.put(1, mat2);

        doReturn(listaMaterias).when(materiaBusiness).obtenerListaMaterias();

        Materia mat3 = new Materia();
        mat3.setNombre("Matematica");
        mat3.setAnio(1);
        mat3.setCuatrimestre(2);
        mat3.setProfesores(new HashSet<Integer>(){{
            add(2); // Se agrega el profesor con el ID:2
        }});
        mat3.setCorrelatividades(new HashSet<Integer>());

        doReturn(mat3).when(materiaBusiness).buscarMateriaByNombre(anyString());

        doReturn(List.of(listaMaterias)).when(materiaBusiness).obtenerListaMateriasOrderedBy(any(MateriaData.OrderMateriaBy.class));
    }

    @Test
    void getMateriaByNombre() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/materia?nombre=Matematica"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists()); // Cambiar el valor esperado según tu lógica

        //Mockito.verify(carreraBusiness, Mockito.times(1)).crearCarrera(Mockito.any(CarreraDto.class));

        doThrow(new MateriaNoEncontradaException()).when(materiaBusiness).buscarMateriaByNombre(anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/materia?nombre=MatematicaAvanzada"))
                .andExpect(MockMvcResultMatchers.status().isNotFound())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica

        doThrow(new NoHayMateriasException()).when(materiaBusiness).buscarMateriaByNombre(anyString());

        mockMvc.perform(MockMvcRequestBuilders.get("/materia?nombre=MatematicaAvanzada"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
    }

    @Test
    void getMateriasByFiltro() throws Exception {

        mockMvc.perform(MockMvcRequestBuilders.get("/materias"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists()); // Cambiar el valor esperado según tu lógica

        mockMvc.perform(MockMvcRequestBuilders.get("/materias?order=nombre_asc"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").exists()); // Cambiar el valor esperado según tu lógica

        mockMvc.perform(MockMvcRequestBuilders.get("/materias?order=DeArribaAbajo"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isString()); // Cambiar el valor esperado según tu lógica

        //Mockito.verify(carreraBusiness, Mockito.times(1)).crearCarrera(Mockito.any(CarreraDto.class));

        doThrow(new NoHayMateriasException()).when(materiaBusiness).obtenerListaMaterias();

        mockMvc.perform(MockMvcRequestBuilders.get("/materias"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica

        doThrow(new IllegalArgumentException()).when(materiaBusiness).obtenerListaMateriasOrderedBy(any(MateriaData.OrderMateriaBy.class));

        mockMvc.perform(MockMvcRequestBuilders.get("/materia?order=DeArribaAbajo"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica


    }

    @Test
    void crearMateria() throws Exception {
        doReturn(0).when(materiaBusiness).crearMateria(any(MateriaDto.class));

        MateriaDto dto = new MateriaDto();
        dto.setNombre("Laboratorio III");
        dto.setAnio(2);
        dto.setCuatrimestre(1);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica

        Mockito.verify(materiaBusiness, Mockito.times(1)).crearMateria(Mockito.any(MateriaDto.class));

        MateriaDto dto2 = new MateriaDto();
        dto2.setNombre("Laboratorio III");
        dto2.setCuatrimestre(1);

        ObjectMapper obj2 = new ObjectMapper();
        String json2 = obj2.writeValueAsString(dto2);

        mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType("application/json")
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        mockMvc.perform(MockMvcRequestBuilders.post("/materia")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void editarMateria() throws Exception {
        doNothing().when(materiaBusiness).editarMateria(anyInt(),any(MateriaDto.class));

        MateriaDto dto = new MateriaDto();
        dto.setNombre("LaboratorioComputacional III");
        dto.setAnio(4);
        dto.setCuatrimestre(2);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/materia/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        doThrow(new MateriaNoEncontradaException()).when(materiaBusiness).editarMateria(anyInt(),any(MateriaDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/carrera/4")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

    }

    @Test
    void eliminarMateria() throws Exception {

        doNothing().when(materiaBusiness).borrarMateria(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/materia/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        doThrow(new MateriaNoEncontradaException()).when(materiaBusiness).borrarMateria(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/materia/4")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        doThrow(new NoHayMateriasException()).when(materiaBusiness).borrarMateria(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/materia/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

    }
}