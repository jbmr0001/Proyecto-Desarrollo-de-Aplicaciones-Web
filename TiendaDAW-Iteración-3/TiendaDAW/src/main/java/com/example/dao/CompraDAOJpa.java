package com.example.dao;

import com.example.model.Compra;
import com.example.model.Producto;
import com.example.model.Usuario;
import com.example.qualifiers.DAOJpa;
import jakarta.enterprise.context.RequestScoped;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import jakarta.persistence.Query;
import jakarta.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@RequestScoped
@DAOJpa 
@Transactional
public class CompraDAOJpa implements CompraDAO,Serializable{

    private final Logger logger = Logger.getLogger(CompraDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Compra> buscaTodos() {
        List<Compra> lista = null;
        try {
            Query q = em.createQuery("Select c from Compra c", Compra.class);
            lista = (List<Compra>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lista;
    }

    @Override
    public boolean guarda(Compra c) {
        boolean guardado = false;
        try {
            em.persist(c);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean modifica(Compra ce) {
        boolean modificado = false;
        try {
            Compra c = null;
            em.merge(ce);
            modificado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return modificado;
    }

    @Override
    public boolean borra(Compra ce) {
        boolean borrado = false;
        try {
            Compra c = null;
            c = em.find(Compra.class, ce.getEmail());
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public Compra buscaById(int id) {
        Compra c=null;
        try {
            c=em.find(Compra.class, id);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return c;
    }

    @Override
    public int getUltimoIDCompra() {
        int ultimoId=0;
        try {
            Query q = em.createQuery("SELECT c.id FROM Compra c ORDER BY c.id DESC");
            q.setMaxResults(1);
            ultimoId = (int)q.getSingleResult();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return ultimoId;
    }
}
