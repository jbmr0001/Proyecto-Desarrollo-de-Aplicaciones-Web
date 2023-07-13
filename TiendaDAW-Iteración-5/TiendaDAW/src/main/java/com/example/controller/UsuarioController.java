package com.example.controller;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import com.example.dao.UsuarioDAOMap;
import com.example.qualifiers.DAOJpa;
import com.example.qualifiers.DAOMap;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.util.logging.Logger;

import java.util.List;

import java.io.Serializable;


@Named("ctrlUsuarios")
@ViewScoped
public class UsuarioController implements Serializable {

    @Inject
    HttpServletRequest request; //acceso al objeto request de la petición actual
    public String logout() throws ServletException {
        request.logout();
        request.getSession().invalidate();
        return "/index.xhtml?faces-redirect=true"; //PRG
    }
    @Inject  @DAOJpa
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
        usuario = usuarioDAO.buscaByEmail( usuario.getEmail() );
    }

    public void registro() throws IOException {
        if(usuarioDAO.buscaByEmail(usuario.getEmail())!=null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Un Usuario con id "+ usuario.getEmail()+" ya existe", null));
        }else{
            usuarioDAO.guarda(usuario);
            paginaUsuario="../usuarios/paginaUsuario.xhtml";
            FacesContext.getCurrentInstance().getExternalContext().redirect(paginaUsuario);
        }


    }

    public void recuperaConEmail(String email) {
        usuario = usuarioDAO.buscaByEmail(email);
    }

    public void crea() throws IOException {
        if(usuarioDAO.buscaByEmail(usuario.getEmail())!=null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" ya existe", null));
        }else{
            usuarioDAO.guarda(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../usuarios/listadoUsuarios.xhtml");
        }

    }

    public void modifica() throws IOException {
        if(usuarioDAO.buscaByEmail(usuario.getEmail())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" no está registrado", null));
        }else{
            usuarioDAO.modifica(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../usuarios/listadoUsuarios.xhtml");
        }
    }

    public void borra(String email) throws IOException, ServletException {
        this.recuperaConEmail(email);
        if(usuarioDAO.buscaByEmail(usuario.getEmail())==null){
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Usuario "+ usuario.getEmail()+" no está registrado", null));
        }else{
            usuario.setEmail(email);
            usuarioDAO.borra(usuario);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../usuarios/listadoUsuarios.xhtml");
        }
    }

    public boolean esAdminPrincipal(String email){
        return email.equals("admin@admin");
    }

}
