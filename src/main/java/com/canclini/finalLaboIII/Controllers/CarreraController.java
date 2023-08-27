package com.canclini.finalLaboIII.Controllers;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Dtos.ResponseDtoJson;
import com.canclini.finalLaboIII.Business.Implementations.CarreraBusiness;
import com.canclini.finalLaboIII.Business.Implementations.DepartamentoBusiness;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;
import jakarta.annotation.Nullable;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping
@Validated
public class CarreraController {
    @Autowired
    private CarreraBusiness carreraBusiness;
    @Autowired
    private DepartamentoBusiness departamentoBusiness;

    @GetMapping("/carrera/{idCarrera}")
    public ResponseEntity<ResponseDtoJson> getCarreraById(@Nullable @PathVariable Integer idCarrera){
        if (idCarrera == null || idCarrera < 0) {
            return ResponseEntity.badRequest().body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "El ID de la carrera tiene que ser mayor a cero", null));
        }
        try {
            Carrera carrera = carreraBusiness.buscarCarreraById(idCarrera);
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Carerra Creada", carrera));
        }
        catch (NoHayCarrerasException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Carreras Cargadas", null));
        }
        catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "Carrera no encontrada", null));
        }
    }
    @GetMapping("/carreras")
    public ResponseEntity<ResponseDtoJson> getCarreras(){
        try {
            Map<Integer, Carrera> carreras = carreraBusiness.obtenerListaCarrera();
            return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Carreras encontradas", carreras));
        }
        catch (NoHayCarrerasException e){
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Carreras Cargadas", null));
        }
    }
    @PostMapping("/carrera")
    public ResponseEntity<ResponseDtoJson> crearCarrera(@Nullable @RequestBody @Valid CarreraDto carrera) throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        if (carrera == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Especifique Correctamente los datos de la Carrera", null));
        }
        try{
            if (departamentoBusiness.obtenerListaDepartamentos().isEmpty()){
                throw new NoHayDepartamentosException();
            }
            if (!departamentoBusiness.obtenerListaDepartamentos().containsKey(carrera.getDepartamentoId())){
                throw new DepartamentoNoEncontradoException();
            }
        }catch (NoHayDepartamentosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Departamentos Cargados", null));
        }
        catch (DepartamentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "Departamento no encontrado", null));
        }

        Integer idCarrera = carreraBusiness.crearCarrera(carrera);
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Carrera Creada Exitosamente", idCarrera));



    }
    @PutMapping("/carrera/{idCarrera}")
    public ResponseEntity<ResponseDtoJson> editarCarrera(@RequestBody @Valid CarreraDto carrera, @PathVariable Integer idCarrera){
        if (carrera == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "Especifique Correctamente los datos de la Carrera", null));
        }
        if (idCarrera == null || idCarrera < 0) {
            return ResponseEntity.badRequest().body(new ResponseDtoJson(HttpStatus.BAD_REQUEST, "El Id de la carrera debe ser un entero mayor o igual a cero", null));
        }
        try{
            carreraBusiness.editarCarrera(idCarrera, carrera);
        }
        catch (NoHayDepartamentosException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Departamentos Cargados", null));
        }
        catch (NoHayCarrerasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Carreras Cargadas", null));
        }catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No se a encontrado la carrera", idCarrera));
        } catch (DepartamentoNoEncontradoException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "No Se Ha Encontrado El Departamento", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Se a Editado la Carrera Correctamente", idCarrera));
    }
    @DeleteMapping("/carrera/{idCarrera}")
    public ResponseEntity<ResponseDtoJson> eliminarCarrera(@PathVariable Integer idCarrera){
        try{
            carreraBusiness.borrarCarrera(idCarrera);
        }catch (CarreraNoEncontradaException e){
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseDtoJson(HttpStatus.NOT_FOUND, "Carrera no encontrada", null));
        } catch (NoHayCarrerasException e) {
            return ResponseEntity.status(HttpStatus.NO_CONTENT).body(new ResponseDtoJson(HttpStatus.NO_CONTENT, "No Hay Carreras Cargadas", null));
        }
        return ResponseEntity.ok(new ResponseDtoJson(HttpStatus.OK, "Carrera Eliminada Correctamente", null));
    }
}
