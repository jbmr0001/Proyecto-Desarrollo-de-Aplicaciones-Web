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

    private Map<Integer, Compra> compras = null;

    private int ultimo;

    public CompraDAOMap() {
        if (compras == null) {
            compras = new HashMap<>();
            compras.put(1, new Compra(1,"user@user",1));
            compras.put(2, new Compra(2,"admin@admin",2));
        }
    }

    public List<Compra> buscaTodos() {
        return compras.values().stream().collect(Collectors.toList());
    }

    public List<Compra> buscaTodosUsuario(String emailbuscado) {
        List<Compra>c=compras.values().stream().collect(Collectors.toList());
        int i=0;
        while(i<c.size()){

            if(c.get(i).getEmail()!=emailbuscado){
                c.remove(i);
                i--;
            }else{
                i++;
            }
        }
        return c;
    }

    public boolean guarda(Compra c){
        ultimo++;
        c.setId(ultimo);
        compras.put(c.getId(),c);
        return true;
    }
    public boolean borra(Compra c){
        compras.remove(c.getEmail());
        return true;
    }

    public boolean modifica(Compra c){
        compras.put(c.getId(),c);
        return true;
    }

    @Override
    public Compra buscaById(int id) {
        Compra c = compras.get(id);
        return c;
    }

    @Override
    public int getUltimoIDCompra() {
        return ultimo;
    }

}
