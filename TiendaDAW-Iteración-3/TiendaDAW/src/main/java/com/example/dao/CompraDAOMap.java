package com.example.dao;

import com.example.model.Compra;
import com.example.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class CompraDAOMap implements CompraDAO, Serializable {

    private Map<String, Compra> compras = null;

    public CompraDAOMap() {
        if (compras == null) {
            compras = new HashMap<>();
            compras.put("usuarioDAO1@gmail.com", new Compra(1,"usuarioDAO1@gmail.com",1));
            compras.put("usuarioDAO2@gmail.com", new Compra(2,"usuarioDAO2@gmail.com",2));
        }
    }

    public List<Compra> buscaTodos() {
        return compras.values().stream().collect(Collectors.toList());
    }

    public boolean guarda(Compra c){
        compras.put(c.getEmail(),c);
        return true;
    }
    public boolean borra(Compra c){
        compras.remove(c.getEmail());
        return true;
    }

    public boolean modifica(Compra c){
        compras.put(c.getEmail(),c);
        return true;
    }

    @Override
    public Compra buscaById(int id) {
        Compra c = compras.get(id);
        return c;
    }

    @Override
    public int getUltimoIDCompra() {
        return compras.size()-1;
    }
}
