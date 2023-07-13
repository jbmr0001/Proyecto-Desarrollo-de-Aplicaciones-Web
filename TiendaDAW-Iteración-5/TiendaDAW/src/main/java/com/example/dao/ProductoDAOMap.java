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
    private int ultimo;
    public ProductoDAOMap() {
        if (productos == null) {
            productos = new HashMap<>();
            productos.put(1, new Producto(1, "Camiseta Manchester United Temporada 2022/2023","Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.",32,"./resources/images/1.jpg",1));
            productos.put(2, new Producto(2, "Camiseta Real Madrid Temporada 2022/2023","Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.",42,"./resources/images/2.jpg",1));
            productos.put(3, new Producto(3, "Camiseta FC Barcelona Temporada 2022/2023", "Su diseño de alto rendimiento con tecnología de ventilación HEAT.RDY te permite emplearte a fondo en el terreno de juego.",10,"./resources/images/3.jpg",1));
            ultimo=3;
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

    public List<Producto> buscaOfertas() {
        List<Integer> claves = new ArrayList<>(productos.keySet());
        List<Producto> ofertas =new ArrayList<>();
        int i=0;
        while(i<claves.size()){
            if(productos.get(claves.get(i)).getPrecio()<=60){
                ofertas.add(productos.get(i));
            }
        }
        return ofertas;
    }

    public List<Producto> buscaUlt() {
        List<Integer> claves = new ArrayList<>(productos.keySet());
        Collections.shuffle(claves);
        List<Producto> ult_unidades =new ArrayList<>();
        int i=0;
        while(i<claves.size()){
            if(productos.get(claves.get(i)).getStock()<=1){
                ult_unidades.add(productos.get(i));
            }
        }
        return ult_unidades;
    }

    @Override
    public int getUltimoIDRegistrado() {
        return this.ultimo;
    }

    @Override
    public void decrementaStock(int id) {

    }


    public boolean guarda(Producto p){
        ultimo++;
        p.setId(ultimo);
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
