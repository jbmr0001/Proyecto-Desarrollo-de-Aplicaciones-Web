package com.example.dao;

import com.example.model.Usuario;
import jakarta.enterprise.context.ApplicationScoped;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@ApplicationScoped
public class UsuarioDAO {
    private Map<String, Usuario> usuarios = null;

    public UsuarioDAO() {
        if (usuarios == null) {
            usuarios = new HashMap<>();
            usuarios.put("usuarioDAO1@gmail.com", new Usuario("usuarioDAO1@gmail.com", "11111111","Pepe","Calle Encina 27","111111111111","admin"));
            usuarios.put("usuarioDAO2@gmail.com", new Usuario("usuarioDAO2@gmail.com", "22222222","Pepe","Calle Encina 27","111111111111","usuario"));
        }
    }

    public Usuario buscaEmail(String email) {
        Usuario usuario = usuarios.get(email);
        return usuario;
    }

    public List<Usuario> buscaTodos() {
        return usuarios.values().stream().collect(Collectors.toList());
    }

    public int numUsuarios() {
        return usuarios.size();
    }

    public void guarda(Usuario usuario){
        usuarios.put(usuario.getEmail(),usuario);
    }
    public void borra(Usuario usuario){
        usuarios.remove(usuario.getEmail());
    }

    public void modifica(Usuario usuario){
        usuarios.put(usuario.getEmail(),usuario);
    }

}
