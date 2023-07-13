package com.example.controller;

import com.example.dao.ProductoDAO;
import com.example.dao.UsuarioDAO;
import com.example.model.Compra;
import com.example.dao.CompraDAO;
import com.example.model.Producto;
import com.example.qualifiers.DAOJpa;
import jakarta.annotation.PostConstruct;
import jakarta.faces.application.FacesMessage;
import jakarta.faces.context.FacesContext;
import jakarta.faces.view.ViewScoped;
import jakarta.inject.Inject;
import jakarta.inject.Named;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;

import java.io.IOException;
import java.lang.ref.ReferenceQueue;
import java.util.logging.Logger;

import java.util.List;
import java.util.Iterator;

import java.io.Serializable;

@Named("ctrlCompra")
@ViewScoped
public class CompraController implements Serializable {
    @Inject
    @DAOJpa
    private CompraDAO compraDAO;
    @Inject
    @DAOJpa
    private ProductoDAO pDAO;

    @Inject
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    //View-Model
    private Compra compra;
    private final Logger logger = Logger.getLogger(ProductoController.class.getName());

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

    public List<Compra> getComprasUsuario(String email) {
        return compraDAO.buscaTodosUsuario(email);
    }

    public void recupera() {
        logger.info("Recuperando compra " + compra.getId());
        compra = compraDAO.buscaById(compra.getId());
    }

    public void comprar(int idProducto, String email) throws IOException {
        if (!pDAO.buscaById(idProducto).hayStock()) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No hay stock disponible lo sentimos", null));
        } else {
            compra.setEmail(email);
            compra.setIdProducto(idProducto);
            compra.setId(this.compraDAO.getUltimoIDCompra() + 1);
            compraDAO.guarda(compra);
            Producto producto = new Producto();
            producto.setId(idProducto);
            //pDAO.borra(producto);
            pDAO.decrementaStock(idProducto);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../index.xhtml");
        }
    }

    public Producto getProductoComprado(int id) {
        return this.pDAO.buscaById(id);
        
    }

    public void crea() throws IOException {
        if(pDAO.buscaById(compra.getIdProducto())==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe producto con id " + compra.getIdProducto(), null));
        }
        if(usuarioDAO.buscaByEmail(compra.getEmail())==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe usuario con email " + compra.getEmail(), null));
        }
        if (FacesContext.getCurrentInstance().getMessageList().isEmpty()){
            compraDAO.guarda(compra);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../ventas/listadoVentas.xhtml");
        }

    }

    public void modifica() throws IOException {
        if(pDAO.buscaById(compra.getIdProducto())==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe producto con id " + compra.getIdProducto(), null));
        }
        if(usuarioDAO.buscaByEmail(compra.getEmail())==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe usuario con email " + compra.getEmail(), null));
        }
        if(compraDAO.buscaById(compra.getId())==null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "No existe una compra con id " + compra.getId(), null));
        }
        if (FacesContext.getCurrentInstance().getMessageList().isEmpty()){
            compraDAO.modifica(compra);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../ventas/listadoVentas.xhtml");
        }
    }

    public void borra(int id) throws IOException {
        this.recuperaConId(id);
        if (compraDAO.buscaById(compra.getId()) == null) {
            FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(FacesMessage.SEVERITY_INFO, "Compra con id " + compra.getId() + " no existe", null));
        } else {
            compraDAO.borra(compra);
            FacesContext.getCurrentInstance().getExternalContext().redirect("../ventas/listadoVentas.xhtml");
        }
    }

    public void recuperaConId(int id) {
        compra = compraDAO.buscaById(id);
    }

    public int getUltimoID(){
        return compraDAO.getUltimoIDCompra();
    }

}
