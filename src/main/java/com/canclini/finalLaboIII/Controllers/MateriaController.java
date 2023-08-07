package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Materia.MateriaDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.MateriaBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData.OrderMateriaBy;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping
@Validated
public class MateriaController {
    @Autowired
    private MateriaBusiness materiaBusiness;
    @GetMapping("/materia")
    public ResponseEntity<?> getMateriaByNombre(@Nullable @RequestParam String nombre){
        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "Ingrese co", null));
        }
        try{
            return ResponseEntity.ok(materiaBusiness.buscarMateriaByNombre(nombre));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No hay Materias");
        }
        catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la materia");
        }
        catch (RuntimeException e){
            return ResponseEntity.notFound().build();
        }

    }
    @GetMapping("/materias")
    public ResponseEntity<?> getMateriasByFiltro(@Nullable @RequestParam String order){
        try {
            if (order == null || order.isEmpty()) {
                return ResponseEntity.ok(materiaBusiness.obtenerListaMaterias());
            }
            OrderMateriaBy orderBy = OrderMateriaBy.valueOf(order.toLowerCase());
            return ResponseEntity.ok(materiaBusiness.obtenerListaMateriasOrderedBy(orderBy));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Los Tipos de Ordenamientos disponibles son: "+ Arrays.toString(OrderMateriaBy.values()));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Hay Materias Cargadas");
        }
    }
    @PostMapping("/materia")
    public ResponseEntity<?> crearMateria(@Nullable @RequestBody @Valid MateriaDto materia){
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los datos de la materia");
        }
        return ResponseEntity.ok(materiaBusiness.crearMateria(materia));
    }
    @PutMapping("/materia/{idMateria}")
    public ResponseEntity<?> editarMateria(@Nullable @RequestBody @Valid MateriaDto materia, @PathVariable Integer idMateria){
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los datos de la materia");
        }
        try{
            materiaBusiness.editarMateria(idMateria, materia);
        }catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la materia");
        }
        return ResponseEntity.ok("Se a Editado la Materia Correctamente");
    }
    @DeleteMapping("/materia/{idMateria}")
    public ResponseEntity<?> eliminarMateria(@PathVariable Integer idMateria){
        try{
            materiaBusiness.borrarMateria(idMateria);
        }catch (MateriaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la materia");
        }
        return ResponseEntity.ok("Materia Eliminada Correctamente");
    }
}
