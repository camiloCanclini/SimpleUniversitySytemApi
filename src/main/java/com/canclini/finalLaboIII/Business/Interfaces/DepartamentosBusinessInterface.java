package com.canclini.finalLaboIII.Business.Interfaces;

import com.canclini.finalLaboIII.Business.Dtos.DepartamentoDto;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;

import java.util.Map;

public interface DepartamentosBusinessInterface {
    public int crearDepartamento(DepartamentoDto departamentoDto) throws NoHayDepartamentosException;
    public void borrarDepartamento(Integer idDepa) throws DepartamentoNoEncontradoException, NoHayDepartamentosException;
    public Map<Integer, String> obtenerListaDepartamentos() throws NoHayDepartamentosException;
}
