package com.example.controller;

import com.example.dao.ProductosDAO;
import com.example.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.util.List;
import java.util.logging.Logger;

import java.io.Serializable;


@Named("ctrlProducto")
@ViewScoped
public class ProductoController implements Serializable {
    @Inject
    private ProductosDAO pDAO;
    //View-Model
    private Producto producto;
    private final Logger logger = Logger.getLogger( ProductoController.class.getName() );

    public ProductoController() {
    }

    @PostConstruct
    private void init() {
        producto = new Producto();
    }

    public Producto getProducto() {
        return producto;
    }

    public void setProducto(Producto p) {
        //método no necesario, se usarán los set de Libro desde vista
        this.producto = p;
    }

    public List<Producto> getProductos() {
        return pDAO.buscaTodos();
    }
    public void recupera() {
        logger.info("Recuperando producto "+producto.getID());
        producto = pDAO.buscaId( producto.getID() );
    }

}

