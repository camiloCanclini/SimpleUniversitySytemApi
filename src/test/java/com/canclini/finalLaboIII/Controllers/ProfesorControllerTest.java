    package com.canclini.finalLaboIII.Controllers;

    import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
    import com.canclini.finalLaboIII.Business.Implementations.ProfesorBusiness;
    import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
    import com.canclini.finalLaboIII.Entity.Profesor;
    import org.junit.jupiter.api.BeforeEach;
    import org.junit.jupiter.api.Test;
    import org.mockito.InjectMocks;
    import org.mockito.Mockito;
    import org.mockito.MockitoAnnotations;
    import org.mockito.Spy;
    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.test.web.servlet.MockMvc;
    import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
    import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
    import org.springframework.test.web.servlet.setup.MockMvcBuilders;

    import java.util.HashMap;
    import java.util.Map;

    import static org.mockito.Mockito.doReturn;
    import static org.mockito.Mockito.when;

    class ProfesorControllerTest {

        @Autowired
        MockMvc mockMvc;

        @Spy
        ProfesorBusiness profesorBusiness;

        @InjectMocks
        ProfesorController profesorController;
        @BeforeEach
            // Se inicia el testeo
        void setUp() throws NoHayProfesoresException {
            this.mockMvc = MockMvcBuilders.standaloneSetup(profesorController).build();
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

            doReturn(listaProfes).when(profesorBusiness).obtenerListaProfesor();
        }
        @Test
        void getProfesores() throws Exception {
            mockMvc.perform(MockMvcRequestBuilders.get("/profesores"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                    //.andExpect(MockMvcResultMatchers.jsonPath("$.message").value(""))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(Mockito.any(Map.class))); // Cambiar el valor esperado según tu lógica
        }

        @Test
        void crearProfesor() throws Exception {
            ProfesorDto mockProfesorDto = new ProfesorDto();
            mockProfesorDto.setNombre("John Doe");

            when(profesorBusiness.crearProfesor(Mockito.any(ProfesorDto.class))).thenReturn(1); // Cambiar el valor de retorno según tu lógica

            mockMvc.perform(MockMvcRequestBuilders.post("/profesor")
                            .contentType("application/json")
                            .content("{ \"nombre\": \"John Doe\" }"))
                    .andExpect(MockMvcResultMatchers.status().isOk())
                    .andExpect(MockMvcResultMatchers.jsonPath("$.status").value("OK"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.message").value("Profesor creado exitosamente"))
                    .andExpect(MockMvcResultMatchers.jsonPath("$.data").value(1)); // Cambiar el valor esperado según tu lógica

            Mockito.verify(profesorBusiness, Mockito.times(1)).crearProfesor(Mockito.any(ProfesorDto.class));
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
        }
    }