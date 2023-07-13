package com.example.controller;

import com.example.dao.ProductoDAO;
import com.example.model.Producto;
import com.example.qualifiers.DAOJpa;
import com.example.tiendadaw.Preferencias;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
import java.util.List;
import java.util.logging.Logger;

import java.io.Serializable;


@Named("ctrlProducto")
@ViewScoped
public class ProductoController implements Serializable {

    @Inject
    Preferencias preferencias;
    @Inject
    @DAOJpa
    private ProductoDAO pDAO;
    //View-Model
    private Producto producto;
    private final Logger logger = Logger.getLogger(ProductoController.class.getName());

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
        this.producto = p;
    }

    public List<Producto> getProductos() {
        return pDAO.buscaTodos();
    }

    public List<Producto> getProductosRec() {
        return pDAO.buscaRec(producto.getId());
    }

    public void recupera() {
        logger.info("Recuperando producto " + producto.getId());
        producto = pDAO.buscaById(producto.getId());

        preferencias.setUltimoProducto(producto);
        preferencias.calculaRecomendados(producto.getId());
    }

    public void crea() throws IOException {
        if (pDAO.buscaById(producto.getId()) != null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Un Producto con id " + producto.getId() + " ya existe", null));
        } else {
            pDAO.guarda(producto);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../productos/listadoProductos.xhtml");
        }

    }

    public void modifica() throws IOException {
        if (pDAO.buscaById(producto.getId()) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto con id " + producto.getId() + " no existe", null));
        } else {
            pDAO.modifica(producto);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../productos/listadoProductos.xhtml");
        }
    }

    public void borra() throws IOException {
        if (pDAO.buscaById(producto.getId()) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto con id " + producto.getId() + " no existe", null));
        } else {
            pDAO.borra(producto);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../productos/listadoProductos.xhtml");
        }
    }

    public int getUltimoID(){
        return pDAO.getUltimoIDRegistrado();
    }

}

