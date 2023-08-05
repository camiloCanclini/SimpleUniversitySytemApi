package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.CarreraDto;
import com.canclini.finalLaboIII.Business.Dtos.DepartamentoDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.DepartamentoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.AlumnoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;
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
public class DepartamentoController {
    @Autowired
    DepartamentoBusiness departamentoBusiness;

    @GetMapping("/departamentos")
    public ResponseEntity<ResponseDtoJson> getDepas(){
        try {
            Map<Integer, String> depas = departamentoBusiness.obtenerListaDepartamentos();
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Departamentos encontrados", depas));
        }
        catch (NoHayDepartamentosException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Departamentos Cargados", null));
        }
    }
    @PostMapping("/departamento")
    public ResponseEntity<ResponseDtoJson> crearDepartamento(@Nullable @RequestBody @Valid DepartamentoDto departamentoDto){
        if (departamentoDto == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Especifique el nombre del departamento", null));
        }
        Integer idDepa = departamentoBusiness.crearDepartamento(departamentoDto);
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Departamento Creado Exitosamente", idDepa));
    }
    @DeleteMapping("/departamento/{idDepa}")
    public ResponseEntity<ResponseDtoJson> eliminarAlumno(@PathVariable Integer idDepa){
        try{
            departamentoBusiness.borrarDepartamento(idDepa);
        }catch (NoHayDepartamentosException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Departamentos Cargados", null));
        }
        catch(DepartamentoNoEncontradoException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se encontró al departamento", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Departamento Eliminado Exitosamente", null));
    }
}
