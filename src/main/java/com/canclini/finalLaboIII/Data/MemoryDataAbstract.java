package com.canclini.finalLaboIII.Data;

import java.util.HashMap;
import java.util.Map;

public abstract class MemoryDataAbstract<T> {
    protected int contadorId = 0;
    protected int generarId(){
        return contadorId++;
    }
    protected Map<Integer, T> lista = new HashMap<>();

    // Otros métodos comunes para las clases concretas de personas
}