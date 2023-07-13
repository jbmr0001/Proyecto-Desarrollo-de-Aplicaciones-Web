package com.example.dao;

import com.example.model.Producto;
import com.example.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.*;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class ProductoDAOMap implements ProductoDAO, Serializable{

    private Map<Integer, Producto> productos = null;
    private int contador=1;
    public ProductoDAOMap() {
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

    public Producto buscaById(int id) {
        Producto p = productos.get(id);
        return p;
    }

    public List<Producto> buscaTodos() {
        return productos.values().stream().collect(Collectors.toList());
    }

    public List<Producto> buscaRec(int _id) {
        List<Integer> claves = new ArrayList<>(productos.keySet());
        Collections.shuffle(claves);
        List<Producto> productosAleatorios =new ArrayList<>();
        if(productos.size()>=4){
            List<Integer> clavesAleatorias = claves.subList(0, 4);
            for (Integer clave : clavesAleatorias) {
                productosAleatorios.add(productos.get(clave));
            }
        }else{
            List<Integer> clavesAleatorias = claves.subList(0, productos.size());
            for (Integer clave : clavesAleatorias) {
                productosAleatorios.add(productos.get(clave));
            }
        }
        return productosAleatorios;
    }

    @Override
    public int getUltimoIDRegistrado() {
        return this.productos.size()-1;
    }


    public boolean guarda(Producto p){
        p.setId(contador);
        contador++;
        productos.put(p.getId(),p);
        return true;
    }
    public boolean borra(Producto p){
        productos.remove(p.getId());
        return true;
    }

    public boolean modifica(Producto p){
        productos.put(p.getId(),p);
        return true;
    }
}
