package com.example.dao;

import com.example.model.Producto;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class ProductosDAO {
    private Map<Integer, Producto> productos = null;
    private int contador=1;
    public ProductosDAO() {
        if (productos == null) {
            productos = new HashMap<>();
            productos.put(1, new Producto(1, "El Ingenioso Hidalgo Don Quijote de la Mancha","Clásico de la literatura española",32));
            productos.put(2, new Producto(2, "The definitive guide to JSF in Java EE 8","A whole new world",42));
            productos.put(3, new Producto(3, "Naruto", "Season 1",10));
            productos.put(4, new Producto(4, "Naruto", "Season 1",10));
            productos.put(5, new Producto(5, "Naruto", "Season 1",10));
            productos.put(6, new Producto(6, "Naruto", "Season 1",10));
            productos.put(7, new Producto(7, "Naruto", "Season 1",10));
            productos.put(8, new Producto(8, "Naruto", "Season 1",10));
            contador=9;
        }
    }

    public Producto buscaId(int id) {
        Producto p = productos.get(id);
        return p;
    }

    public List<Producto> buscaTodos() {
        return productos.values().stream().collect(Collectors.toList());
    }

    public List<Producto> buscaRec(int _id) {
        List <Producto> l=new ArrayList<>();
        int contador =0;

        while (contador<4) {
            if (_id+contador<productos.size()) {
                l.add(productos.get(_id+contador)); 
            }
            else{
                if (_id-contador>0) {
                    l.add(productos.get(_id-contador)); 
                }  
            }
            contador++;
        }

        return l;
    }

    public int numProductos() {
        return productos.size();
    }

    public void guarda(Producto p){
        p.setId(contador);
        contador++;
        productos.put(p.getId(),p);
    }
    public void borra(Producto p){
        productos.remove(p.getId());
    }

    public void modifica(Producto p){
        productos.put(p.getId(),p);
    }
}

