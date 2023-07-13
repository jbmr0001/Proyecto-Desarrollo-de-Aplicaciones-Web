package com.example.controller;

import com.example.dao.ProductoDAO;
import com.example.model.Compra;
import com.example.dao.CompraDAO;
import com.example.model.Producto;
import com.example.qualifiers.DAOJpa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.List;

import java.io.Serializable;

@Named("ctrlCompra")
@ViewScoped
public class CompraController implements Serializable {
    @Inject  @DAOJpa
    private CompraDAO compraDAO;
    @Inject  @DAOJpa
    private ProductoDAO pDAO;


    //View-Model
    private Compra compra;
    private final Logger logger = Logger.getLogger( ProductoController.class.getName() );

    public CompraController() {
    }

    @PostConstruct
    private void init() {
        compra = new Compra();
    }

    public Compra getCompra() {
        return compra;
    }

    public void setCompra(Compra c) {
        this.compra = c;
    }

    public List<Compra> getCompras() {
        return compraDAO.buscaTodos();
    }

    public void recupera() {
        logger.info("Recuperando compra "+compra.getId());
        compra= compraDAO.buscaById( compra.getId() );
    }

    public void comprar(int idProducto,String email) throws IOException{

        compra.setEmail(email);
        compra.setIdProducto(idProducto);
        compra.setId(this.compraDAO.getUltimoIDCompra()+1);
        compraDAO.guarda(compra);
        Producto producto =new Producto();
        producto.setId(idProducto);
        pDAO.borra(producto);
        FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
    }


}
