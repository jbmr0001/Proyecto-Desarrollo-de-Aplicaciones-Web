package com.example.tiendadaw;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import com.example.qualifiers.DAOJpa;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;
import jakarta.security.enterprise.identitystore.IdentityStore;

import java.util.*;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import java.util.Set;
import java.util.logging.Logger;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.security.enterprise.credential.UsernamePasswordCredential;
import jakarta.security.enterprise.identitystore.CredentialValidationResult;

import static jakarta.security.enterprise.identitystore.CredentialValidationResult.INVALID_RESULT;

import jakarta.security.enterprise.identitystore.IdentityStore;

@ApplicationScoped
public class TiendaDAWIdentityStore implements IdentityStore {
    private Map<String, String> credenciales; //ejemplo de almacén de credenciales
    @Inject
    @DAOJpa
    private UsuarioDAO usuariosDAO;


    public TiendaDAWIdentityStore() {
        credenciales = new HashMap<>();
        credenciales.put("usuario1", "clave1");
        credenciales.put("usuario2", "clave2");
    }

    public CredentialValidationResult validate(UsernamePasswordCredential usernamePasswordCredential) {

        //Recuperar credenciales proporcionadas por el servidor
        String email = usernamePasswordCredential.getCaller();
        String password = usernamePasswordCredential.getPasswordAsString();

        Usuario usuario = usuariosDAO.buscaByEmail(email);
        if (usuario != null) {
            if (usuario.getEmail().equals(email) && usuario.getContraseña().equals(password)) {
                    //Autenticación completada, obtener los roles del usuario...
                Set<String> roles;
                if(usuario.getAdmin()==true){
                    roles = new HashSet<>(Arrays.asList("ADMINISTRADORES"));
                }else{
                    roles = new HashSet<>(Arrays.asList("USUARIOS"));
                }

                    //Pasar datos del usuario al servidor
                    return new CredentialValidationResult(email, roles);
                }
        }
        return INVALID_RESULT; //Autenticación inválida
    }
}
