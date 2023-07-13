package com.example.dao;

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
@DAOJpa //Qualifier para esta implementación
@Transactional
public class UsuarioDAOJpa implements UsuarioDAO, Serializable {

    private final Logger logger = Logger.getLogger(UsuarioDAOJpa.class.getName());
    @PersistenceContext
    private EntityManager em;

    @Override
    public List<Usuario> buscaTodos() {
        List<Usuario> lista = null;
        try {
            Query q = em.createQuery("Select u from Usuario u", Usuario.class);
            lista = (List<Usuario>)q.getResultList();
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return lista;
    }

    @Override
    public boolean guarda(Usuario usuario) {
        boolean guardado = false;
        try {
            em.persist(usuario);
            guardado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return guardado;
    }

    @Override
    public boolean modifica(Usuario usuario) {
        boolean modificado = false;
        try {
            Usuario c = null;
            em.merge(usuario);
            modificado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return modificado;
    }

    @Override
    public boolean borra(Usuario usuario) {
        boolean borrado = false;
        try {
            Usuario c = null;
            c = em.find(Usuario.class, usuario.getEmail());
            em.remove(c);
            borrado = true;
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);
        }
        return borrado;
    }

    @Override
    public Usuario buscaByEmail(String email) {
        Usuario u=null;
        try {
            u=em.find(Usuario.class, email);
        } catch (Exception ex) {
            logger.log(Level.SEVERE, ex.getMessage(), ex);

        }
        return u;
    }
}
