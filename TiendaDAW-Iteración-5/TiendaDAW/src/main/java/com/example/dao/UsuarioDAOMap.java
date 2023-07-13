package com.example.dao;

import com.example.model.Usuario;
import com.example.qualifiers.DAOMap;
import jakarta.enterprise.context.ApplicationScoped;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
@DAOMap
public class UsuarioDAOMap implements UsuarioDAO, Serializable {
    private Map<String, Usuario> usuarios = null;

    public UsuarioDAOMap() {
        if (usuarios == null) {
            usuarios = new HashMap<>();
            usuarios.put("usuarioDAO1@gmail.com", new Usuario("usuarioDAO1@gmail.com", "11111111","Pepe","Calle Encina 27","111111111111",true));
            usuarios.put("usuarioDAO2@gmail.com", new Usuario("usuarioDAO2@gmail.com", "22222222","Pepe","Calle Encina 27","111111111111",false));
        }
    }

    public Usuario buscaByEmail(String email) {
        Usuario usuario = usuarios.get(email);
        return usuario;
    }

    public List<Usuario> buscaTodos() {
        return usuarios.values().stream().collect(Collectors.toList());
    }

    public boolean guarda(Usuario usuario){
        usuarios.put(usuario.getEmail(),usuario);
        return true;
    }
    public boolean borra(Usuario usuario){
        usuarios.remove(usuario.getEmail());
        return true;
    }

    public boolean modifica(Usuario usuario){
        usuarios.put(usuario.getEmail(),usuario);
        return true;
    }

}
