package com.example.dao;

import java.util.List;

public interface PlantillaDAO<T,K> {

    public List<T> buscaTodos();
    public boolean guarda(T c);
    //public boolean borra(K id);
    public boolean modifica(T c);

    public boolean borra(T c);
}
