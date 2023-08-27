package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.Carrera.CarreraDto;
import com.canclini.finalLaboIII.Data.Exceptions.CarreraNoEncontradaException;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayCarrerasException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;

import java.util.HashSet;
import java.util.Map;

public interface CarreraBusinessInterface {
    public int crearCarrera(CarreraDto carrera) throws DepartamentoNoEncontradoException, NoHayDepartamentosException, NoHayCarrerasException;
    public void borrarCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public void editarCarrera(int idCarrera, CarreraDto carrera) throws NoHayDepartamentosException, DepartamentoNoEncontradoException, NoHayCarrerasException, CarreraNoEncontradaException;
    public Object buscarCarreraById(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public HashSet<Integer> obtenerListaMateriaDeCarrera(int idCarrera) throws NoHayCarrerasException, CarreraNoEncontradaException;
    public Map<Integer, Carrera> obtenerListaCarrera() throws NoHayCarrerasException;
}
