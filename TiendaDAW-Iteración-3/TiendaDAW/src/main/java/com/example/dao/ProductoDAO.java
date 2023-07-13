package com.example.dao;

import com.example.model.Producto;

import java.util.List;

public interface ProductoDAO extends PlantillaDAO<Producto,Integer>{
    
    public Producto buscaById(int id);
    public List<Producto> buscaRec(int id);

    public int getUltimoIDRegistrado();
}

