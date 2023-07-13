package com.example.controller;

import com.example.model.Usuario;
import com.example.dao.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import java.util.logging.Logger;

import java.util.List;

import java.io.Serializable;


@Named("ctrlUsuarios")
@ViewScoped
public class UsuarioController implements Serializable {
    @Inject
    private UsuarioDAO usuarioDAO;
    //View-Model
    private Usuario usuario;
    private final Logger logger = Logger.getLogger( UsuarioController.class.getName() );

    public UsuarioController() {
    }

    @PostConstruct
    private void init() {
        usuario = new Usuario();
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {

        this.usuario = usuario;
    }

    public List<Usuario> getUsuarios() {
        return usuarioDAO.buscaTodos();
    }

    public void recupera() {
        logger.info("Recuperando libro "+ usuario.getEmail());
        usuario = usuarioDAO.buscaEmail( usuario.getEmail() );
    }

    public void guarda(String correo,String contraseña){
        usuarioDAO.guarda(new Usuario(correo,contraseña));
    }

    public void borra(String correo,String contraseña){
        usuarioDAO.borra(new Usuario(correo,contraseña));
    }
}
