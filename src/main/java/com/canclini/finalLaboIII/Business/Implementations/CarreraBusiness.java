package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Interfaces.CarreraBusinessInterface;
import com.canclini.finalLaboIII.Data.Implementations.CarreraData;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public class CarreraBusiness implements CarreraBusinessInterface {
    CarreraData carreraData = new CarreraData();
    @Override
    public int crearCarrera(Carrera carrera) {
        return carreraData.crearCarrera(carrera);
    }

    @Override
    public void borrarCarrera(int idCarrera) {
        carreraData.borrarCarrera(idCarrera);
    }

    @Override
    public void editarCarrera(int idCarrera, Carrera carrera) {
        carreraData.editarCarrera(idCarrera, carrera);
    }

    @Override
    public Object buscarCarreraById(int idCarrera) {
        return carreraData.buscarCarreraById(idCarrera);
    }

    @Override
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriaDeCarrera(int idCarrera) {
        return carreraData.obtenerListaMateriaDeCarrera(idCarrera);
    }

    @Override
    public Map<Integer, Integer> obtenerListaCarrera() {
        return carreraData.obtenerListaCarrera();
    }
}
