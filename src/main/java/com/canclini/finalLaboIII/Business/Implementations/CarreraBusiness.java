package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.CarreraDto;
import com.canclini.finalLaboIII.Business.Interfaces.CarreraBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Implementations.CarreraData;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
@Service
public class CarreraBusiness implements CarreraBusinessInterface {
    @Autowired
    CarreraData carreraData;
    @Autowired
    private DepartamentoData departamentoData;
    @Override
    public int crearCarrera(CarreraDto carrera) throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        departamentoData.obtenerListaDepartamentos().containsKey(carrera.getDepartamentoId());
        return carreraData.crearCarrera(new Carrera(carrera.getNombre(), carrera.getCantidadAnios(), new ArrayList<Map.Entry<Integer, Materia>>(), carrera.getDepartamentoId()));
    }

    @Override
    public void borrarCarrera(int idCarrera) {
        carreraData.borrarCarrera(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, CarreraDto carrera) {
        carreraData.editarCarrera(idCarrera, new Carrera(carrera.getNombre(), carrera.getCantidadAnios(), null, carrera.getDepartamentoId()));
    }

    @Override
    public Carrera buscarCarreraById(int idCarrera) {
        return carreraData.buscarCarreraById(idCarrera);
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriaDeCarrera(int idCarrera) {
        return carreraData.obtenerListaMateriaDeCarrera(idCarrera);
    }

    @Override
    public Map<Integer, Carrera> obtenerListaCarrera() {
        return carreraData.obtenerListaCarrera();
    }
}
