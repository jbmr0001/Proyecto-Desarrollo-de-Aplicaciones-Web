package com.example.dao;

import com.example.model.Compra;
import com.example.model.Usuario;

public interface CompraDAO extends PlantillaDAO<Compra,Integer> {
    public Compra buscaById(int id);
    public int getUltimoIDCompra();
}
