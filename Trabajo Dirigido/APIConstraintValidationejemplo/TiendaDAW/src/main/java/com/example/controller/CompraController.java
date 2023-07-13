package com.example.controller;

import com.example.model.Compra;
import com.example.model.Producto;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Named;

import java.io.Serializable;

@Named("ctrlCompra")
@ViewScoped
public class CompraController implements Serializable {

    private Compra compra;
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

    public void crea(){

    }
    public void modifica(){

    }
}


