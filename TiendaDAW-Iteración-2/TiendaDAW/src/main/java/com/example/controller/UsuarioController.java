package com.example.controller;

import com.example.model.Usuario;
import com.example.dao.UsuarioDAO;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;

import java.io.IOException;
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

    private String paginaUsuario;
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
        logger.info("Recuperando usuario "+ usuario.getEmail());
        usuario = usuarioDAO.buscaEmail( usuario.getEmail() );
    }

    public void registro() throws IOException {
        usuarioDAO.guarda(usuario);
        paginaUsuario="../usuarios/paginaUsuario.xhtml?email=" + usuario.getEmail();
        FacesContext.getCurrentInstance().getExternalContext().redirect(paginaUsuario);

    }

    public void recuperaConEmail(String email) {
        usuario = usuarioDAO.buscaEmail(email);
    }

    public void crea() {
        usuarioDAO.guarda(usuario);
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" registrado", null));
    }

    public void modifica(){
        if(usuarioDAO.buscaEmail(usuario.getEmail())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" no está registrado", null));
        }else{
            usuarioDAO.modifica(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" modificado", null));
        }
    }

    public void borra(){
        if(usuarioDAO.buscaEmail(usuario.getEmail())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" no está registrado", null));
        }else{
            usuarioDAO.borra(usuario);
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" borrado", null));
        }
    }

    

}
