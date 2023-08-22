package com.canclini.finalLaboIII.Business.Implementations;

import com.canclini.finalLaboIII.Business.Dtos.DepartamentoDto;
import com.canclini.finalLaboIII.Business.Interfaces.DepartamentosBusinessInterface;
import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Implementations.DepartamentoData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Map;
@Service
public class DepartamentoBusiness implements DepartamentosBusinessInterface {
    @Autowired
    DepartamentoData departamentoData;
    @Override
    public int crearDepartamento(DepartamentoDto departamentoDto) throws NoHayDepartamentosException {
        return departamentoData.crearDepartamento(departamentoDto.getNombre());
    }

    @Override
    public void borrarDepartamento(Integer idDepa) throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        departamentoData.borrarDepartamento(idDepa);
    }

    @Override
    public Map<Integer, String> obtenerListaDepartamentos() throws NoHayDepartamentosException {
        return departamentoData.obtenerListaDepartamentos();
    }
}
