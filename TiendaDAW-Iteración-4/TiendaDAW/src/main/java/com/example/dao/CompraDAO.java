package com.example.dao;

import com.example.model.Compra;
import com.example.model.Usuario;

import java.util.List;

public interface CompraDAO extends PlantillaDAO<Compra,Integer> {
    public Compra buscaById(int id);
    public int getUltimoIDCompra();
    public List<Compra> buscaTodosUsuario(String emailbuscado);

}
