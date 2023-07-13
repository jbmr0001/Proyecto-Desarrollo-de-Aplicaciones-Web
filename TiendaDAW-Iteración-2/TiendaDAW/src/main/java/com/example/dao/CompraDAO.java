package com.example.dao;

import com.example.model.Compra;
import com.example.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class CompraDAO {
    private Map<String, Compra> compras = null;

    public CompraDAO() {
        if (compras == null) {
            compras = new HashMap<>();
            compras.put("usuarioDAO1@gmail.com", new Compra(1,"usuarioDAO1@gmail.com",1));
            compras.put("usuarioDAO2@gmail.com", new Compra(2,"usuarioDAO2@gmail.com",2));
        }
    }

    public Compra buscaId(int id) {
        Compra c = compras.get(id);
        return c;
    }

    public List<Compra> buscaTodos() {
        return compras.values().stream().collect(Collectors.toList());
    }

    public int numCompras() {
        return compras.size();
    }

    public void guarda(Compra c){
        compras.put(c.getEmail(),c);
    }
    public void borra(Compra c){
        compras.remove(c.getEmail());
    }

    public void modifica(Compra c){
        compras.put(c.getEmail(),c);
    }
}
