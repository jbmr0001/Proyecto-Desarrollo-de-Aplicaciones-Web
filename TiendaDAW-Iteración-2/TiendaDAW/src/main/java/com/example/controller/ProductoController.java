package com.example.controller;

import com.example.dao.ProductosDAO;
import com.example.model.Producto;
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
        this.producto = p;
    }

    public List<Producto> getProductos() {
        return pDAO.buscaTodos();
    }

    public List<Producto> getProductosRec() {
        return pDAO.buscaRec(producto.getId());
    }
    public void recupera() {
        logger.info("Recuperando producto "+producto.getId());
        producto = pDAO.buscaId( producto.getId() );
    }

    public void crea() {
        pDAO.guarda(producto);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto "+ producto.getId()+" registrado", null));
    }

    public void modifica(){
        if(pDAO.buscaId(producto.getId())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto "+ producto.getId()+" no esta en la lista", null));
        }else{
            pDAO.modifica(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto "+ producto.getId()+" modificado", null));
        }
    }

    public void borra(){
        if(pDAO.buscaId(producto.getId())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto "+ producto.getId()+" no esta en la lista", null));
        }else{
            pDAO.borra(producto);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Producto "+ producto.getId()+" borrado", null));
        }
    }

}

