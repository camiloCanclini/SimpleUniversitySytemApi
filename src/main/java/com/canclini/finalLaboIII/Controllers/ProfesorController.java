package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorDto;
import com.canclini.finalLaboIII.Business.Dtos.Profesor.ProfesorMateriaDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.ProfesorBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.MateriaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayMateriasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayProfesoresException;
import com.canclini.finalLaboIII.Data.Exceptions.ProfesorNoEncontradoException;
import com.canclini.finalLaboIII.Entity.Profesor;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@Validated
public class ProfesorController {
    @Autowired
    private ProfesorBusiness profesorBusiness;
    @GetMapping("/profesores")
    public ResponseEntity<ResponseDtoJson> getProfesores(){
        try {
            Map<Integer, Profesor> profesores = profesorBusiness.obtenerListaProfesor();
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, null, profesores));
        }
        catch (NoHayProfesoresException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
    }
    @PostMapping("/profesor")

    public ResponseEntity<ResponseDtoJson> crearProfesor(@RequestBody @Valid ProfesorDto profesor){

    if (profesor == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese la entidad profesor", null));
    }
    Integer idNuevoProfesor = profesorBusiness.crearProfesor(profesor);
    return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Profesor creado exitosamente", idNuevoProfesor));

    }
    /*@PutMapping("/profesor/{idProfesor}")
    public ResponseEntity<ResponseDtoJson> agregarMateria(@Nullable @RequestBody @Valid ProfesorMateriaDto materia, @PathVariable Integer idProfesor){
        if (materia == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese el idMateria", null));
        }
        try{
            profesorBusiness.agregarMateria(idProfesor, materia.getIdMateria());
        }catch (ProfesorNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el profesor", null));
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró la materia", null));
        } catch (NoHayMateriasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No hay materias cargadas", null));
        } catch (NoHayProfesoresException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No hay profesores cargados", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Se ha agregado la materia al profesor", null));
    }
    @DeleteMapping("/profesor/{idProfesor}/{idMateria}")
    public ResponseEntity<ResponseDtoJson> agregarMateria(@PathVariable Integer idMateria, @PathVariable Integer idProfesor){
        if (idMateria == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese el idMateria", null));
        }
        try{
            profesorBusiness.borrarMateria(idProfesor, idMateria);
        }catch (ProfesorNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el profesor", null));
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró la materia", null));
        } catch (NoHayMateriasException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No Hay Materias Cargadas", null));
        } catch (NoHayProfesoresException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No hay profesores cargados", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Se ha borrado la materia al profesor", null));
    }

     */
    @DeleteMapping("/profesor/{idProfesor}")
    public ResponseEntity<ResponseDtoJson> eliminarProfesor(@PathVariable Integer idProfesor){
        try{
            profesorBusiness.borrarProfesor(idProfesor);
        }catch (ProfesorNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el profesor", null));
        } catch (NoHayProfesoresException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No hay profesores cargados", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Profesor Eliminado Exitosamente", null));
    }

}
