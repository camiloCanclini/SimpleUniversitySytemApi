package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.AlumnoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.AsignaturaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayAlumnosException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import jakarta.annotation.Nullable;
import jakarta.validation.ConstraintViolationException;
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
public class AlumnoController {
    @Autowired
    private AlumnoBusiness alumnoBusiness;
    @GetMapping("/alumnos")
    public ResponseEntity<ResponseDtoJson> getAlumnos(){
        try {
            Map<Integer, Alumno> alumnos = alumnoBusiness.obtenerListaAlumnos();
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, null, alumnos));
        }
        catch (NoHayAlumnosException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
    }
    @PostMapping("/alumno")

    public ResponseEntity<?> crearAlumno(@RequestBody @Valid AlumnoDto alumno){
        try{
            if (alumno == null) {
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los Datos del Alumno");
            }
            return ResponseEntity.ok(alumnoBusiness.crearAlumno(alumno));
        }catch (ConstraintViolationException e){
            return ResponseEntity.badRequest().build();
        }
    }
    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<?> editarAlumno(@Nullable @RequestBody AlumnoDto alumno, @PathVariable Integer idAlumno){
        if (alumno == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los Datos del Alumno");
        }
        try{
            alumnoBusiness.editarAlumno(idAlumno, alumno);
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado el Alumno");
        }
        return ResponseEntity.ok("Se a Editado el Alumno Correctamente");
    }
    @DeleteMapping("/alumno/{idAlumno}")
    public ResponseEntity<?> eliminarAlumno(@PathVariable Integer idAlumno){
        try{
            alumnoBusiness.borrarAlumno(idAlumno);
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado el Alumno");
        }
        return ResponseEntity.ok("Alumno Eliminado Correctamente");
    }

    @PostMapping("/alumno/{idAlumno}/asignatura")
    public ResponseEntity<?> aniadirAsignaturaAlumno(@PathVariable Integer idAlumno, @Nullable @RequestBody Asignatura asignatura){
        if (asignatura == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los Datos de la Asignatura");
        }
        try{
            return ResponseEntity.ok(alumnoBusiness.aniadirAsignatura(idAlumno, asignatura));
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado el Alumno");
        }
    }
    @PutMapping("/alumno/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<?> cambiarEstadoAsignatura(@PathVariable Integer idAlumno, @PathVariable Integer idAsignatura, @Nullable @RequestBody String estado){
        if (estado == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique El Estado de la ASignatura");
        }
        try{
            alumnoBusiness.cambiarEstadoAsignatura(idAlumno, idAsignatura, Asignatura.Estado.valueOf(estado.toUpperCase()));
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado el Alumno");
        }
        catch (AsignaturaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la Asignatura");
        }
        return ResponseEntity.ok("Se a Cambiado el Estado de Asignatura Correctamente");
    }
}
