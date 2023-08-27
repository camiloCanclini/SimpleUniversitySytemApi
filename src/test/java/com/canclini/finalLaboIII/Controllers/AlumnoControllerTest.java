package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Implementations.AlumnoBusiness;
import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Business.Implementations.DepartamentoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import com.canclini.finalLaboIII.Entity.Carrera;
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

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.*;

class AlumnoControllerTest {

    MockMvc mockMvc;

    @Spy // Se simula la dependencia de la clase que estamos testeando, en este caso puedo
    private AlumnoBusiness alumnoBusiness;

    @InjectMocks // Se inyecta lo anterior en la clase que se va a testear
    private AlumnoController alumnoController;

    @BeforeEach
    void setUp() throws NoHayCarrerasException, NoHayDepartamentosException, NoHayAlumnosException, AlumnoNoEncontradoException, MateriaNoEncontradaException, NoHayMateriasException, AsignaturaNoEncontradaException, EstadoAsignaturaNoPermitidoException {
        MockitoAnnotations.openMocks(this);
        this.mockMvc = MockMvcBuilders.standaloneSetup(alumnoController).build();

        Alumno alumno = new Alumno();
        alumno.setNombre("Camilo");
        alumno.setApellido("Canclini");
        alumno.setDni(44453822L);
        alumno.setAsignaturas(new HashMap<>() {{

            Asignatura asig = new Asignatura(0, Asignatura.Estado.NO_CURSADA, null);
            Asignatura asig2 = new Asignatura(1, Asignatura.Estado.CURSADA, null);
            Asignatura asig3 = new Asignatura(2, Asignatura.Estado.APROBADA, 10);

            put(0, asig);
            put(1, asig2);
            put(2, asig3);
        }});


        HashMap<Integer, Alumno> listaAlumnos = new HashMap<>();
        listaAlumnos.put(0, alumno);

        doReturn(listaAlumnos).when(alumnoBusiness).obtenerListaAlumnos();
        doReturn(alumno).when(alumnoBusiness).buscarAlumnoById(anyInt());
        doReturn(0).when(alumnoBusiness).aniadirAsignatura(anyInt(), any(AsignaturaDto.class));
        doNothing().when(alumnoBusiness).cambiarEstadoAsignatura(anyInt(), anyInt(), any(AsignaturaEditarDto.class));
    }

    @Test
    void getAlumnos() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/alumnos"))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isMap()); // Cambiar el valor esperado según tu lógica

        doThrow(new NoHayAlumnosException()).when(alumnoBusiness).obtenerListaAlumnos();

        mockMvc.perform(MockMvcRequestBuilders.get("/alumnos"))
                .andExpect(MockMvcResultMatchers.status().isNoContent())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NO_CONTENT"))
                //.andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
    }

    @Test
    void crearAlumno() throws Exception {
        doReturn(0).when(alumnoBusiness).crearAlumno(any(AlumnoDto.class));

        AlumnoDto dto = new AlumnoDto();
        dto.setNombre("Gustavo");
        dto.setApellido("Ceratti");
        dto.setDni(23153833L);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica

        Mockito.verify(alumnoBusiness, Mockito.times(1)).crearAlumno(Mockito.any(AlumnoDto.class));

        AlumnoDto dto2 = new AlumnoDto();
        dto2.setNombre("Gustavo");
        //dto2.setApellido("Ceratti");
        dto2.setDni(23153833L);

        ObjectMapper obj2 = new ObjectMapper();
        String json2 = obj2.writeValueAsString(dto2);

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno")
                        .contentType("application/json")
                        .content(json2))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());
    }

    @Test
    void editarAlumno() throws Exception {
        doNothing().when(alumnoBusiness).editarAlumno(anyInt(),any(AlumnoEditarDto.class));

        AlumnoEditarDto dto = new AlumnoEditarDto();
        dto.setNombre("Camilo Stephano");
        dto.setApellido("Canclini");

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk());

        dto.setApellido(null);

        mockMvc.perform(MockMvcRequestBuilders.put("/carrera/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void eliminarAlumno() throws Exception {
        doNothing().when(alumnoBusiness).borrarAlumno(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/alumno/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isOk());

        doThrow(new AlumnoNoEncontradoException()).when(alumnoBusiness).borrarAlumno(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/alumno/4")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        doThrow(new NoHayAlumnosException()).when(alumnoBusiness).borrarAlumno(anyInt());

        mockMvc.perform(MockMvcRequestBuilders.delete("/alumno/0")
                        .contentType("application/json"))
                .andExpect(MockMvcResultMatchers.status().isNoContent());
    }

    @Test
    void aniadirAsignaturaAlumno() throws Exception {

        AsignaturaDto dto = new AsignaturaDto();
        dto.setIdMateria(0);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno/0/asignatura")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0));

        doThrow(new NoHayMateriasException()).when(alumnoBusiness).aniadirAsignatura(anyInt(), any(AsignaturaDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno/4/asignatura")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        doThrow(new AlumnoNoEncontradoException()).when(alumnoBusiness).aniadirAsignatura(anyInt(), any(AsignaturaDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno/4/asignatura")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        doThrow(new NoHayAlumnosException()).when(alumnoBusiness).aniadirAsignatura(anyInt(), any(AsignaturaDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno/4/asignatura")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNoContent());

        doThrow(new MateriaNoEncontradaException()).when(alumnoBusiness).aniadirAsignatura(anyInt(), any(AsignaturaDto.class));

        mockMvc.perform(MockMvcRequestBuilders.post("/alumno/4/asignatura")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());
    }

    @Test
    void cambiarEstadoAsignatura() throws Exception {

        AsignaturaEditarDto dto = new AsignaturaEditarDto();
        dto.setEstado("Cursada");
        dto.setNota(5);

        ObjectMapper obj = new ObjectMapper();
        String json = obj.writeValueAsString(dto);

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/0/asignatura/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isOk())
                .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

        doThrow(new EstadoAsignaturaNoPermitidoException()).when(alumnoBusiness).cambiarEstadoAsignatura(anyInt(), anyInt(), any(AsignaturaEditarDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/0/asignatura/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isBadRequest());

        doThrow(new AlumnoNoEncontradoException()).when(alumnoBusiness).cambiarEstadoAsignatura(anyInt(), anyInt(), any(AsignaturaEditarDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/0/asignatura/0")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());

        doThrow(new AsignaturaNoEncontradaException()).when(alumnoBusiness).cambiarEstadoAsignatura(anyInt(), anyInt(), any(AsignaturaEditarDto.class));

        mockMvc.perform(MockMvcRequestBuilders.put("/alumno/0/asignatura/5")
                        .contentType("application/json")
                        .content(json))
                .andExpect(MockMvcResultMatchers.status().isNotFound());



    }
}