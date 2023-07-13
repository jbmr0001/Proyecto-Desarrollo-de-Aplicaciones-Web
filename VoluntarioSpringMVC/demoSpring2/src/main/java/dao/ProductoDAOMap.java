package dao;

import model.Producto;
import org.springframework.stereotype.Repository;
import org.springframework.web.context.annotation.RequestScope;

import java.util.*;
import java.util.stream.Collectors;
@Repository("productosDAOMap")
public class ProductoDAOMap implements ProductoDAO {

    private Map<Integer, Producto> productos = null;
    public ProductoDAOMap() {
        if (productos == null) {
            productos = new HashMap<>();
            productos.put(1, new Producto(1, "El Ingenioso Hidalgo Don Quijote de la Mancha","Clásico de la literatura española",32));
            productos.put(2, new Producto(2, "The definitive guide to JSF in Java EE 8","A whole new world",42));
            productos.put(3, new Producto(3, "Naruto", "Season 1",10));

        }
    }

    @Override
    public Producto buscaById(int id) {
        Producto p = productos.get(id);
        return p;
    }

    public List<Producto> buscaTodos() {
        return productos.values().stream().collect(Collectors.toList());
    }

    public boolean guarda(Producto p){
        productos.put(p.getId(),p);
        return true;
    }

    public boolean modifica(Producto p){
        productos.put(p.getId(),p);
        return true;
    }
    @Override
    public boolean borra(Producto p) {
        productos.remove(p.getId());
        return true;
    }
}



