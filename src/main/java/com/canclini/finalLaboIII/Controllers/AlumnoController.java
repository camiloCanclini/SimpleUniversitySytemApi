package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Implementations.AlumnoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.AsignaturaNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayAlumnosException;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
import jakarta.annotation.Nullable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
public class AlumnoController {
    private final AlumnoBusiness alumnoBusiness = new AlumnoBusiness();
    @GetMapping("/alumnos")
    public ResponseEntity<?> getAlumnos(){
        try {
            return ResponseEntity.ok(alumnoBusiness.obtenerListaAlumnos());
        }
        catch (NoHayAlumnosException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body("No Hay Alumnos Cargados");
        }
    }
    @PostMapping("/alumno")
    public ResponseEntity<?> crearAlumno(@Nullable @RequestBody Alumno alumno){
        if (alumno == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Especifique Correctamente los Datos del Alumno");
        }
        return ResponseEntity.ok(alumnoBusiness.crearAlumno(alumno));
    }
    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<?> editarAlumno(@Nullable @RequestBody Alumno alumno, @PathVariable Integer idAlumno){
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
            alumnoBusiness.cambiarEstadoAsignatura(idAlumno, idAsignatura, Asignatura.Estado.valueOf(estado));
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado el Alumno");
        }
        catch (AsignaturaNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se a encontrado la Asignatura");
        }
        return ResponseEntity.ok("Se a Cambiado el Estado de Asignatura Correctamente");
    }
}
