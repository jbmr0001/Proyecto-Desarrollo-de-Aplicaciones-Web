package dao;

import model.Producto;

import java.util.List;

public interface ProductoDAO {
    public Producto buscaById(int id);
    public List<Producto> buscaTodos();
    public boolean guarda(Producto p);
    public boolean modifica(Producto p);
    public boolean borra(Producto p);

}
