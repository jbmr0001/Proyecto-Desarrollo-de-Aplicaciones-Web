package dao;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import model.Producto;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Repository("productosDAOJpa")
@Transactional
public class ProductoDAOJpa implements ProductoDAO {

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
    public boolean guarda(Producto c) {
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



}
