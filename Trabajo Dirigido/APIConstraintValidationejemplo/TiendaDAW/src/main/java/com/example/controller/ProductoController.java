package com.example.controller;

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

    private Producto producto;
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

    public void crea(){

    }

    public void modifica(){

    }


}

