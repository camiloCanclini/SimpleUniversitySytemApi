package com.canclini.finalLaboIII.Data.Interfaces;

import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Entity.Carrera;
import java.util.Map;

public interface DepartamentoDataInterface {
    public int crearDepartamento(String nombreDepa) throws NoHayDepartamentosException;
    public void borrarDepartamento(Integer idDepa) throws DepartamentoNoEncontradoException, NoHayDepartamentosException;
    public Map<Integer, String> obtenerListaDepartamentos() throws NoHayDepartamentosException;
}
