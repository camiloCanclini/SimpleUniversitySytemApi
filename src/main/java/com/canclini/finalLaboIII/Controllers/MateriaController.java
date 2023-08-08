package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.MateriaBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData.OrderMateriaBy;
import com.canclini.finalLaboIII.Entity.Materia;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping
@Validated
public class MateriaController {
    @Autowired
    private MateriaBusiness materiaBusiness;
    @GetMapping("/materia")
    public ResponseEntity<ResponseDtoJson> getMateriaByNombre(@Nullable @RequestParam String nombre){
        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "Ingrese el nombre de la Materia", null));
        }
        try{
            Materia materia = materiaBusiness.buscarMateriaByNombre(nombre);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoJson(HttpStatus.OK, "Materia Encontrada", materia));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Materias Cargadas", null));
        }
        catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se ha encontrado la Materia", null));
        }
    }
    @GetMapping("/materias")
    public ResponseEntity<ResponseDtoJson> getMateriasByFiltro(@Nullable @RequestParam String order){
        try {
            if (order == null || order.isEmpty()) {
                Map<Integer, Materia> listaMaterias = materiaBusiness.obtenerListaMaterias();
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Lista de Materias sin ORDER", listaMaterias));
            }
            OrderMateriaBy orderBy = OrderMateriaBy.valueOf(order.toLowerCase());
            List<Map.Entry<Integer, Materia>> listaMateriasOrdenadas = materiaBusiness.obtenerListaMateriasOrderedBy(orderBy);
            return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoJson(HttpStatus.OK, "Lista de Materias con ORDER", listaMateriasOrdenadas));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese un tipo de ordenamiento(order)", Arrays.toString(OrderMateriaBy.values())));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Materias Cargadas", null));
        }
    }
    @PostMapping("/materia")
    public ResponseEntity<ResponseDtoJson> crearMateria(@Nullable @RequestBody @Valid MateriaDto materiadto){
        if (materiadto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Especifique Correctamente los datos de la materia", null));
        }
        int materiaId = materiaBusiness.crearMateria(materiadto);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoJson(HttpStatus.OK, "Materia Creada Correctamente", materiaId));
    }
    @PutMapping("/materia/{idMateria}")
    public ResponseEntity<ResponseDtoJson> editarMateria(@Nullable @RequestBody @Valid MateriaDto materia, @PathVariable Integer idMateria){
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Especifique Correctamente los datos de la materia", null));
        }
        try{
            materiaBusiness.editarMateria(idMateria, materia);
        }
        catch (NoHayMateriasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Materias Cargadas", null));
        }
        catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "Materia No Encontrada", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoJson(HttpStatus.OK, "Materia Editada Correctamente", null));
    }
    @DeleteMapping("/materia/{idMateria}")
    public ResponseEntity<ResponseDtoJson> eliminarMateria(@PathVariable Integer idMateria){
        try{
            materiaBusiness.borrarMateria(idMateria);
        }
        catch (NoHayMateriasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Materias Cargadas", null));
        }
        catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se ha encontrado la Materia", null));
        }
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseDtoJson(HttpStatus.OK, "La Materia Se Ha Eliminado Correctamente", null));
    }
}
