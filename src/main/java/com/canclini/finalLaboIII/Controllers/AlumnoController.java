package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoDto;
import com.canclini.finalLaboIII.Business.Dtos.Alumno.AlumnoEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaDto;
import com.canclini.finalLaboIII.Business.Dtos.Asignatura.AsignaturaEditarDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.AlumnoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.*;
import com.canclini.finalLaboIII.Entity.Alumno;
import com.canclini.finalLaboIII.Entity.Asignatura;
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

    public ResponseEntity<ResponseDtoJson> crearAlumno(@RequestBody @Valid AlumnoDto alumno){

    if (alumno == null) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese la entidad alumno", null));
    }
    Integer idNuevoAlumno = alumnoBusiness.crearAlumno(alumno);
    return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Alumno creado exitosamente", null));

    }
    @PutMapping("/alumno/{idAlumno}")
    public ResponseEntity<ResponseDtoJson> editarAlumno(@Nullable @RequestBody @Valid AlumnoEditarDto alumno, @PathVariable Integer idAlumno){
        if (alumno == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese la entidad alumno", null));
        }
        try{
            alumnoBusiness.editarAlumno(idAlumno, alumno);
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el alumno", null));
        } catch (NoHayAlumnosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Se ha editado correctamente el alumno", null));
    }
    @DeleteMapping("/alumno/{idAlumno}")
    public ResponseEntity<ResponseDtoJson> eliminarAlumno(@PathVariable Integer idAlumno){
        try{
            alumnoBusiness.borrarAlumno(idAlumno);
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el alumno", null));
        } catch (NoHayAlumnosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Alumno Eliminado Exitosamente", null));
    }

    @PostMapping("/alumno/{idAlumno}/asignatura")
    public ResponseEntity<ResponseDtoJson> aniadirAsignaturaAlumno(@PathVariable Integer idAlumno, @Nullable @RequestBody @Valid AsignaturaDto asignatura){
        if (asignatura == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Ingrese correctamente los datos de la Asignatura", null));
        }
        try{
            Integer idAsignatura = alumnoBusiness.aniadirAsignatura(idAlumno, asignatura);
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "La asignatura se añadió correctamente", idAsignatura));
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el alumno", null));
        } catch (MateriaNoEncontradaException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró la materia", null));
        } catch (NoHayMateriasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Materias Cargadas", null));
        } catch (NoHayAlumnosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
    }
    @PutMapping("/alumno/{idAlumno}/asignatura/{idAsignatura}")
    public ResponseEntity<ResponseDtoJson> cambiarEstadoAsignatura(@PathVariable Integer idAlumno, @PathVariable Integer idAsignatura, @Nullable @RequestBody @Valid AsignaturaEditarDto asignaturaEditarDto){
        try{
            assert asignaturaEditarDto != null;
            alumnoBusiness.cambiarEstadoAsignatura(idAlumno, idAsignatura, asignaturaEditarDto);
        }catch (AlumnoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró el alumno", null));
        }
        catch (AsignaturaNoEncontradaException | EstadoAsignaturaNoPermitidoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró la Asignatura", null));
        } catch (NoHayAlumnosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, e.getMessage(), null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Estado de asignatura cambiado correctamente", null));
    }
}
