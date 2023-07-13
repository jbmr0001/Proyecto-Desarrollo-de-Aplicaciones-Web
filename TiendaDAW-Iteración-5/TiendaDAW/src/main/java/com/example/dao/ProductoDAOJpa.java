package com.example.dao;

import com.example.model.Producto;
import com.example.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa 
@Transactional
public class ProductoDAOJpa implements ProductoDAO, Serializable{
    
    private final Logger logger = Logger.getLogger(ProductoDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Producto> buscaTodos() {
        List<Producto> lista = null;
        try {
            Query q = em.createQuery("Select p from Producto p", Producto.class);
            lista = (List<Producto>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lista;
    }

    @Override
    public List<Producto> buscaOfertas() {
        List<Producto> lista = null;
        try {
            int precio=60;
            Query q = em.createQuery("Select p from Producto p where p.precio <= :precio", Producto.class);
            q.setParameter("precio",precio);
            lista = (List<Producto>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lista;
    }

    @Override
    public List<Producto> buscaUlt() {
        List<Producto> lista = null;
        try {
            int stock=1;
            Query q = em.createQuery("Select p from Producto p where p.stock <= :stock", Producto.class);
            q.setParameter("stock",stock);
            lista = (List<Producto>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lista;
    }

    @Override
    public boolean guarda(Producto c) {
        boolean guardado = false;
        int id=this.getUltimoIDRegistrado()+1;
        c.setId(id);
        try {
            em.persist(c);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean modifica(Producto ce) {
        boolean modificado = false;
        try {
            Producto c = null;
            em.merge(ce);
            modificado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return modificado;
    }

    @Override
    public boolean borra(Producto ce) {
        boolean borrado = false;
        try {
            Producto c = null;
            c = em.find(Producto.class, ce.getId());
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }
    @Override
    public Producto buscaById(int id) {
        Producto p = null;
        try {
            p=em.find(Producto.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return p;
    }

    @Override
    public List<Producto> buscaRec(int _id) {
        List<Producto>productos =this.buscaTodos();
        Collections.shuffle(productos);
        List<Producto> productosAleatorios;
        if(productos.size()>=4){
            productosAleatorios= productos.subList(0, 4);
        }else{
            productosAleatorios = productos.subList(0, productos.size());
        }
        return productosAleatorios;
    }

    @Override
    public int getUltimoIDRegistrado() {
        int ultimoId=0;
        try {
            Query q = em.createQuery("SELECT p.id FROM Producto p ORDER BY p.id DESC");
            q.setMaxResults(1);
            ultimoId = (int)q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ultimoId;
    }

    @Override
    public void decrementaStock(int id) {
        Producto c=this.buscaById(id);
        c.decrementaStock();
        this.modifica(c);
    }

}
