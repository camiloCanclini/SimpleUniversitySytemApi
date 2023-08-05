package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Entity.Carrera;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping
@Validated
public class CarreraController {
    private final CarreraBusiness carreraBusiness = new CarreraBusiness();

    @GetMapping("/carrera/{idCarrera}")
    public ResponseEntity<?> getCarreraById(@Nullable @PathVariable Integer idCarrera){
        if (idCarrera == null || idCarrera < 0) {
            return ResponseEntity.badRequest().body("El Id de la carrera debe ser un entero mayor o igual a cero");
        }
        try {
            return ResponseEntity.ok(carreraBusiness.buscarCarreraById(idCarrera));
        }
        catch (NoHayCarrerasException e){
            return ResponseEntity.ok("No Hay Carreras Cargadas");
        }
        catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No Se Ha Encontrado La Carrera");
        }
    }
    @GetMapping("/carreras")
    public ResponseEntity<?> getCarreras(){
        try {
            return ResponseEntity.ok(carreraBusiness.obtenerListaCarrera());
        }
        catch (NoHayCarrerasException e){
            return ResponseEntity.ok("No Hay Materias Cargadas");
        }
    }
    @PostMapping("/carrera")
    public ResponseEntity<?> crearCarrera(@Nullable @RequestBody Carrera carrera){
        if (carrera == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los datos de la Carrera");
        }
        return ResponseEntity.ok(carreraBusiness.crearCarrera(carrera));
    }
    @PutMapping("/carrera/{idCarrera}")
    public ResponseEntity<?> editarCarrera(@Nullable @RequestBody Carrera carrera, @PathVariable Integer idCarrera){
        if (carrera == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los datos de la carrera");
        }
        if (idCarrera == null || idCarrera < 0) {
            return ResponseEntity.badRequest().body("El Id de la carrera debe ser un entero mayor o igual a cero");
        }
        try{
            carreraBusiness.editarCarrera(idCarrera, carrera);
        }catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la carrera");
        }
        return ResponseEntity.ok("Se a Editado la Materia Correctamente");
    }
    @DeleteMapping("/carrera/{idCarrera}")
    public ResponseEntity<?> eliminarCarrera(@PathVariable Integer idCarrera){
        try{
            carreraBusiness.borrarCarrera(idCarrera);
        }catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la carrera");
        }
        return ResponseEntity.ok("Carrera Eliminada Correctamente");
    }
}
