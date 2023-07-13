package com.example.controller;

import com.example.model.Usuario;

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


@Named("ctrlUsuario")
@ViewScoped
public class UsuarioController implements Serializable {

    private Usuario usuario;
    public UsuarioController() {
    }

    @PostConstruct
    private void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario u) {
        this.usuario=u;
    }

    public void crea(){

    }

    public void modifica(){

    }

    public void registro(){

    }


}


