package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;

import java.util.HashSet;
import java.util.Map;

public interface CarreraBusinessInterface {
    public int crearCarrera(CarreraDto carrera) throws DepartamentoNoEncontradoException, NoHayDepartamentosException;
    public void borrarCarrera(int idCarrera);
    public void editarCarrera(int idCarrera, CarreraDto carrera) throws NoHayDepartamentosException, DepartamentoNoEncontradoException;
    public Object buscarCarreraById(int idCarrera);
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera);
    public Map<Integer, Carrera> obtenerListaCarrera();
}
