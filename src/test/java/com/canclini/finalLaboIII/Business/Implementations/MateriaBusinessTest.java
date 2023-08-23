package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData;
import com.canclini.finalLaboIII.Entity.Materia;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.mockito.Spy;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.doReturn;

class MateriaBusinessTest {

    @Spy
    MateriaData materiaData;

    @InjectMocks
    MateriaBusiness materiaBusiness;

    @BeforeEach
    void setUp() throws NoHayMateriasException {
        MockitoAnnotations.openMocks(this);

        Materia mat = new Materia();
        mat.setNombre("Matematica I");
        mat.setAnio(1);
        mat.setCuatrimestre(1);
        mat.setCorrelatividades(new HashSet<Integer>());

        Materia mat2 = new Materia();
        mat2.setNombre("Fisica II");
        mat2.setAnio(1);
        mat2.setCuatrimestre(2);
        mat2.setCorrelatividades(new HashSet<Integer>());

        HashMap<Integer, Materia> listaMaterias = new HashMap<>();
        listaMaterias.put(0, mat);
        listaMaterias.put(1, mat2);

        doReturn(listaMaterias).when(materiaData).obtenerListaMaterias();
    }

    @Test
    void crearMateria() {
        MateriaDto dto = new MateriaDto();
        dto.setNombre("Matematica I");
        dto.setAnio(1);
        dto.setCuatrimestre(1);

        MateriaDto dto2 = new MateriaDto();
        dto2.setNombre("Fisica II");
        dto2.setAnio(1);
        dto2.setCuatrimestre(2);

        Integer result = materiaBusiness.crearMateria(dto);
        Integer result2 = materiaBusiness.crearMateria(dto);

        assertNotNull(result); // Verificar que el resultado no sea nulo
        assertNotNull(result2); // Verificar que el resultado no sea nulo

        assertTrue(result >= 0); // Verificar que el resultado sea mayor a 0
        assertTrue(result2 > result);

        //System.out.println(result);
        //System.out.println(result2);

        assertDoesNotThrow(()->{
            materiaBusiness.crearMateria(dto);
            materiaBusiness.crearMateria(dto2);
        });
    }

    @Test
    void borrarMateria() {
        assertDoesNotThrow(()->{
            materiaBusiness.borrarMateria(1);
        });

        assertThrows(MateriaNoEncontradaException.class,()->{
            materiaBusiness.borrarMateria(5);
        });
    }

    @Test
    void editarMateria() throws MateriaNoEncontradaException, NoHayMateriasException {

        String nuevoNombre = "Matematica Aplicada I";
        Integer nuevoCuatri = 2;
        MateriaDto dto = new MateriaDto();
        dto.setNombre(nuevoNombre); // Editada
        dto.setAnio(1);
        dto.setCuatrimestre(nuevoCuatri); // Editada

        Materia materiaAntes = materiaBusiness.obtenerListaMaterias().get(0);

        /*for (Materia mat: materiaBusiness.obtenerListaMaterias().values()) {
            System.out.println(mat.toString());
        }*/

        materiaBusiness.editarMateria(0, dto);

        /*for (Materia mat: materiaBusiness.obtenerListaMaterias().values()) {
            System.out.println(mat.toString());
        }*/

        Materia materiaDespues = materiaBusiness.obtenerListaMaterias().get(0);

        assertEquals(materiaAntes.getAnio(), materiaDespues.getAnio()); // NO se modifico

        assertEquals(materiaDespues.getNombre(), nuevoNombre);
        assertEquals(materiaDespues.getCuatrimestre(), nuevoCuatri);

        assertNotEquals(materiaAntes.getCuatrimestre(), materiaDespues.getCuatrimestre());
        assertNotEquals(materiaAntes.getNombre(), materiaDespues.getNombre());
    }

    @Test
    void buscarMateriaById() {
        assertDoesNotThrow(()->{
            materiaBusiness.buscarMateriaById(0);
        });
        assertThrows(MateriaNoEncontradaException.class, ()->{
            materiaBusiness.buscarMateriaById(4);
        });
    }

    @Test
    void buscarMateriaByNombre() {
        assertDoesNotThrow(()->{
            materiaBusiness.buscarMateriaByNombre("Matematica I");
        });
        assertThrows(MateriaNoEncontradaException.class, ()->{
            materiaBusiness.buscarMateriaByNombre("Matematica III");
        });
    }
    /*
    @Test
    void obtenerListaMaterias() throws NoHayMateriasException {

    }
    */
    @Test
    void obtenerListaMateriasOrderedBy() throws NoHayMateriasException {

        Materia mat = new Materia();
        mat.setNombre("Matematica I");
        mat.setAnio(1);
        mat.setCuatrimestre(1);
        mat.setCorrelatividades(new HashSet<Integer>());

        Materia mat2 = new Materia();
        mat2.setNombre("Fisica II");
        mat2.setAnio(1);
        mat2.setCuatrimestre(2);
        mat2.setCorrelatividades(new HashSet<Integer>());

        Materia mat3 = new Materia();
        mat3.setNombre("Arte Visual I");
        mat3.setAnio(1);
        mat3.setCuatrimestre(2);
        mat3.setCorrelatividades(new HashSet<Integer>());

        Materia mat4 = new Materia();
        mat4.setNombre("Computacion Cuantica I");
        mat4.setAnio(1);
        mat4.setCuatrimestre(1);
        mat4.setCorrelatividades(new HashSet<Integer>());

        HashMap<Integer, Materia> listaMaterias = new HashMap<>();
        listaMaterias.put(0, mat);
        listaMaterias.put(1, mat2);
        listaMaterias.put(2, mat3);
        listaMaterias.put(3, mat4);

        doReturn(listaMaterias).when(materiaData).obtenerListaMaterias();

        /*for (Materia materia: materiaBusiness.obtenerListaMaterias().values()) {
            System.out.println(materia.toString());
        }*/

        Integer codigoAnterior = null;

        for (Map.Entry<Integer, Materia> materia: materiaBusiness.obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy.codigo_asc)) {
            if (codigoAnterior != null) {
                assertTrue(materia.getKey() > codigoAnterior);
            }
            codigoAnterior = materia.getKey();
        }

        codigoAnterior = null;
        for (Map.Entry<Integer, Materia> materia: materiaBusiness.obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy.codigo_desc)) {
            if (codigoAnterior != null) {
                assertTrue(materia.getKey() < codigoAnterior);
            }
            codigoAnterior = materia.getKey();
        }

        ArrayList<String> nombresMateriasOrdenadas = new ArrayList<>();
        for (Map.Entry<Integer, Materia> materia : materiaBusiness.obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy.nombre_asc)) {
            nombresMateriasOrdenadas.add(materia.getValue().getNombre());
        }
        Assertions.assertThat(nombresMateriasOrdenadas).isSorted();

        ArrayList<String> nombresMateriasOrdenadasReversed = new ArrayList<>();
        for (Map.Entry<Integer, Materia> materia : materiaBusiness.obtenerListaMateriasOrderedBy(MateriaData.OrderMateriaBy.nombre_desc)) {
            nombresMateriasOrdenadasReversed.add(materia.getValue().getNombre());
        }
        Assertions.assertThat(nombresMateriasOrdenadasReversed).isSortedAccordingTo(Comparator.reverseOrder());

    }
}