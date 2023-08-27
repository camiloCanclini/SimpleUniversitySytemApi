    package com.canclini.finalLaboIII.Controllers;

    import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
    import com.canclini.finalLaboIII.Business.Implementations.ProfesorBusiness;
    import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
    import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
    import com.canclini.finalLaboIII.Entity.Profesor;
    import com.fasterxml.jackson.databind.ObjectMapper;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.junit.jupiter.api.extension.ExtendWith;
    import org.mockito.InjectMocks;
    import org.mockito.Mockito;
    import org.mockito.MockitoAnnotations;
    import org.mockito.Spy;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.test.context.junit.jupiter.SpringExtension;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;

    import java.util.HashMap;
    import java.util.Map;

    import static org.mockito.Mockito.*;

    class ProfesorControllerTest {
        MockMvc mockMvc;

        @Spy
        ProfesorBusiness profesorBusiness;

        @InjectMocks
        ProfesorController profesorController;
        @BeforeEach
            // Se inicia el testeo
        void setUp() throws NoHayProfesoresException {
            MockitoAnnotations.openMocks(this);
            this.mockMvc = MockMvcBuilders.standaloneSetup(profesorController).build();

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

            doReturn(listaProfes).when(profesorBusiness).obtenerListaProfesor();
        }
        @Test
        void getProfesores() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/profesores"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").isMap()); // Cambiar el valor esperado según tu lógica
            doThrow(new NoHayProfesoresException()).when(profesorBusiness).obtenerListaProfesor();
            mockMvc.perform(MockMvcRequestBuilders.get("/profesores"))
                    .andExpect(MockMvcResultMatchers.status().isNoContent())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NO_CONTENT"))
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
        }

        @Test
        void crearProfesor() throws Exception {

            doReturn(0).when(profesorBusiness).crearProfesor(any(ProfesorDto.class));

            ProfesorDto dto = new ProfesorDto();
            dto.setNombre("Camilo");
            dto.setApellido("Canclini");
            dto.setDni(44453822L);
            dto.setTitulo("Tecnico");

            ObjectMapper obj = new ObjectMapper();
            String json = obj.writeValueAsString(dto);

            mockMvc.perform(MockMvcRequestBuilders.post("/profesor")
                            .contentType("application/json")
                            .content(json))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Profesor creado exitosamente"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica

            Mockito.verify(profesorBusiness, Mockito.times(1)).crearProfesor(Mockito.any(ProfesorDto.class));

            ProfesorDto dto2 = new ProfesorDto();
            dto2.setNombre("Camilo");
            //dto2.setApellido("Canclini");
            dto2.setDni(44453822L);
            dto2.setTitulo("Tecnico");

            ObjectMapper obj2 = new ObjectMapper();
            String json2 = obj2.writeValueAsString(dto2);

            mockMvc.perform(MockMvcRequestBuilders.post("/profesor")
                            .contentType("application/json")
                            .content(json2))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"));
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica

            ProfesorDto dto3 = new ProfesorDto();
            dto3.setNombre("Camilo");
            dto3.setApellido("Canclini");
            //dto3.setDni(44453822L);
            dto3.setTitulo("Tecnico");

            ObjectMapper obj3 = new ObjectMapper();
            String json3 = obj3.writeValueAsString(dto3);

            mockMvc.perform(MockMvcRequestBuilders.post("/profesor")
                            .contentType("application/json")
                            .content(json3))
                    .andExpect(MockMvcResultMatchers.status().isBadRequest());
//                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("BAD_REQUEST"))
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.data").value(0)); // Cambiar el valor esperado según tu lógica



        }

        @Test
        void eliminarProfesor() throws Exception {
            Integer mockIdProfesor = 1;

            Mockito.doNothing().when(profesorBusiness).borrarProfesor(mockIdProfesor);

            mockMvc.perform(MockMvcRequestBuilders.delete("/profesor/1"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Profesor Eliminado Exitosamente"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty());

            Mockito.verify(profesorBusiness, Mockito.times(1)).borrarProfesor(mockIdProfesor);

            doThrow(new ProfesorNoEncontradoException()).when(profesorBusiness).borrarProfesor(anyInt());

            mockMvc.perform(MockMvcRequestBuilders.delete("/profesor/3"))
                    .andExpect(MockMvcResultMatchers.status().isNotFound())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("NOT_FOUND"))
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.message").isString())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").isEmpty()); // Cambiar el valor esperado según tu lógica
        }
    }