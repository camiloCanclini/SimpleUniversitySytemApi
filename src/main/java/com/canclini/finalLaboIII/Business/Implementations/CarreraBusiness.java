package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Business.Interfaces.CarreraBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Implementations.CarreraData;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import com.canclini.finalLaboIII.Entity.Carrera;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Map;
@Service
public class CarreraBusiness implements CarreraBusinessInterface {
    @Autowired
    CarreraData carreraData;
    @Autowired
    private DepartamentoData departamentoData;
    @Override
    public int crearCarrera(CarreraDto carreraDto) throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        if (!departamentoData.obtenerListaDepartamentos().containsKey(carreraDto.getDepartamentoId())) {
         throw new DepartamentoNoEncontradoException();
        }
        Carrera nuevaCarrera = new Carrera(carreraDto.getNombre(), carreraDto.getCantidadAnios(), carreraDto.getDepartamentoId(), new HashSet<>());
        return carreraData.crearCarrera(nuevaCarrera);
    }

    @Override
    public void borrarCarrera(int idCarrera) {
        carreraData.borrarCarrera(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, CarreraDto carreraDto) throws NoHayDepartamentosException, DepartamentoNoEncontradoException {
        if (!departamentoData.obtenerListaDepartamentos().containsKey(carreraDto.getDepartamentoId())) {
            throw new DepartamentoNoEncontradoException();
        }
        Carrera carrera = carreraData.buscarCarreraById(idCarrera);
        if (!(carreraDto.getNombre() == null)) {
            carrera.setNombre(carreraDto.getNombre());
        }
        if (!(carreraDto.getCantidadAnios() == null)) {
            carrera.setCantidadAnios(carreraDto.getCantidadAnios());
        }
        Carrera carreraEditada = new Carrera(carrera.getNombre(), carrera.getCantidadAnios(), carrera.getDepartamentoId(), new HashSet<>());
        carreraData.editarCarrera(idCarrera, carreraEditada);
    }

    @Override
    public Carrera buscarCarreraById(int idCarrera) {
        return carreraData.buscarCarreraById(idCarrera);
    }

    @Override
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera) {
        return carreraData.obtenerListaMateriaDeCarrera(idCarrera);
    }

    @Override
    public Map<Integer, Carrera> obtenerListaCarrera() {
        return carreraData.obtenerListaCarrera();
    }
}
