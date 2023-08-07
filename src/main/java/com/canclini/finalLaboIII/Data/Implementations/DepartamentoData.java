package com.canclini.finalLaboIII.Data.Implementations;

import com.canclini.finalLaboIII.Data.Exceptions.DepartamentoNoEncontradoException;
import com.canclini.finalLaboIII.Data.Exceptions.NoHayDepartamentosException;
import com.canclini.finalLaboIII.Data.Interfaces.DepartamentoDataInterface;
import com.canclini.finalLaboIII.Data.MemoryDataAbstract;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.Map;
@Repository
public class DepartamentoData extends MemoryDataAbstract<String> implements DepartamentoDataInterface {


    @Override
    public int crearDepartamento(String nombreDepa) {
        int id = generarId();
        lista.put(id, nombreDepa);
        return id;
    }

    @Override
    public void borrarDepartamento(Integer idDepa) throws DepartamentoNoEncontradoException, NoHayDepartamentosException {
        if (lista.isEmpty()) {
            throw new NoHayDepartamentosException();
        }
        if (!lista.containsKey(idDepa)) {
            throw new DepartamentoNoEncontradoException();
        }
        lista.remove(idDepa);
    }

    @Override
    public Map<Integer, String> obtenerListaDepartamentos() throws NoHayDepartamentosException {
        if (lista.isEmpty()) {
            throw new NoHayDepartamentosException();
        }
        return lista;
    }
}
