package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Implementations.MateriaBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Implementations.MateriaData.OrderMateriaBy;
import com.canclini.finalLaboIII.Entity.Materia;
import jakarta.annotation.Nullable;
import lombok.NonNull;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.Map;

@RestController
@RequestMapping
public class MateriaController {

    private final MateriaBusiness materiaBusiness = new MateriaBusiness();
    @GetMapping("/materia")
    public ResponseEntity<?> getMateriaByNombre(@Nullable @RequestParam String nombre){
        if (nombre == null || nombre.isEmpty()) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique el nombre de la materia (/materia?nombre='nombremateria')");
        }
        try{
            return ResponseEntity.ok(materiaBusiness.buscarMateriaByNombre(nombre));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la materia");
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
            OrderMateriaBy orderBy = OrderMateriaBy.valueOf(order.toUpperCase());
            return ResponseEntity.ok(materiaBusiness.obtenerListaMateriasOrderedBy(orderBy));
        } catch (IllegalArgumentException ex) {
            return ResponseEntity.badRequest().body("Los Tipos de Ordenamientos disponibles son: "+ Arrays.toString(OrderMateriaBy.values()));
        }
        catch (NoHayMateriasException e){
            return ResponseEntity.ok("No Hay Materias Cargadas");
        }
    }
    @PostMapping("/materia")
    public ResponseEntity<?> crearMateria(@Nullable @RequestBody Materia materia){
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los datos de la materia");
        }
        return ResponseEntity.ok(materiaBusiness.crearMateria(materia));
    }
    @PutMapping("/materia/{idMateria}")
    public ResponseEntity<?> editarMateria(@Nullable @RequestBody Materia materia, @PathVariable Integer idMateria){
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
