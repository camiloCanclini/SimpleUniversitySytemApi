package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.CarreraDto;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;
import com.canclini.finalLaboIII.Entity.Materia;

import java.util.List;
import java.util.Map;

public interface CarreraBusinessInterface {
    public int crearCarrera(CarreraDto carrera) throws DepartamentoNoEncontradoException, NoHayDepartamentosException;
    public void borrarCarrera(int idCarrera);
    public void editarCarrera(int idCarrera, CarreraDto carrera);
    public Object buscarCarreraById(int idCarrera);
    public List<Map.Entry<Integer, Materia>> obtenerListaMateriaDeCarrera(int idCarrera);
    public Map<Integer, Carrera> obtenerListaCarrera();
}
