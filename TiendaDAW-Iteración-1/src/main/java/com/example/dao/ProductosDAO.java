package com.example.dao;

import com.example.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductosDAO {
    private Map<Integer, Producto> productos = null;

    public ProductosDAO() {
        if (productos == null) {
            productos = new HashMap<>();
            productos.put(1, new Producto(1, "El Ingenioso Hidalgo Don Quijote de la Mancha","Clásico de la literatura española",32));
            productos.put(2, new Producto(2, "The definitive guide to JSF in Java EE 8","A whole new world",42));
            productos.put(3, new Producto(3, "Naruto", "Season 1",10));
        }
    }

    public Producto buscaId(int id) {
        Producto p = productos.get(id);
        return p;
    }

    public List<Producto> buscaTodos() {
        return productos.values().stream().collect(Collectors.toList());
    }

    public int numProductos() {
        return productos.size();
    }


}

