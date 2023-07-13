package com.example.tiendadaw;

import com.example.dao.ProductoDAO;
import com.example.model.Producto;
import com.example.qualifiers.DAOJpa;
import jakarta.enterprise.context.SessionScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.Serializable;
import java.util.List;

@Named( value="prefs")
@SessionScoped
public class Preferencias implements Serializable {
    private Producto ultimoProducto= null;
    @Inject
    @DAOJpa
    private ProductoDAO pDAO;
    private List<Producto> recomendados;
    public Preferencias() { };

    public Producto getUltimoProducto() {
        return ultimoProducto;
    }

    public List<Producto> calculaRecomendados(int id) {
        this.recomendados = pDAO.buscaRec(id);
        if(pDAO.buscaById(this.ultimoProducto.getId())!=null){
            this.recomendados.set(0,this.ultimoProducto);
        }
        return recomendados;
    }

    public void setUltimoProducto(Producto ultimoProducto) {
        this.ultimoProducto = ultimoProducto;
    }

    public List<Producto> getRecomendados() {
        return recomendados;
    }
}
