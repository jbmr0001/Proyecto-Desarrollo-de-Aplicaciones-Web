package com.example.dao;

import com.example.model.Usuario;

public interface UsuarioDAO extends PlantillaDAO<Usuario,Integer>{
    //Declare here specific methods for EntityDAO
    public Usuario buscaByEmail(String email);
}
