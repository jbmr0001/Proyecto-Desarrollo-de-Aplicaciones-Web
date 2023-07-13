package com.example.api;

import com.example.dao.UsuarioDAO;
import com.example.model.Usuario;
import com.example.qualifiers.DAOJpa;
import jakarta.inject.Inject;
import jakarta.validation.Valid;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import jakarta.enterprise.context.RequestScoped;
import jakarta.ws.rs.*;

@Path("/usuarios")
@RequestScoped //Ojo, jakarta.enterprise.context.RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class UsuariosResource {

    @Inject
    @DAOJpa
    private UsuarioDAO usuarioDAO;

    @GET
    public List<Usuario> listadoUsuarios() {
        List<Usuario> lista= usuarioDAO.buscaTodos();
        return lista;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearUsuario(@Valid Usuario usuario ) {
        if(usuarioDAO.buscaByEmail(usuario.getEmail())!=null){
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "Ya existe un usuario con email: "+usuario.getEmail());
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        }else{
            usuarioDAO.guarda(usuario);
            return Response.ok(usuario).build();
        }
    }

    @GET
    @Path("/{email}")
    public Response mostrarUsuario(@PathParam("email") String email) {
        if(usuarioDAO.buscaByEmail(email)==null) {
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningún usuario con email: "+email);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            Usuario usuario=usuarioDAO.buscaByEmail(email);
            return Response.ok(usuario).build();
        }
    }

    @DELETE
    @Path("/{email}")
    public Response borrarUsuario(@PathParam("email") String email) {
        Usuario usuario=new Usuario();
        usuario.setEmail(email);
        if (usuarioDAO.borra(usuario)!=true) {

            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningún usuario con email: "+email);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            return Response.ok(email).build();
        }
    }

    @PUT
    @Path("/{email}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarUsuario(@Valid Usuario usuario, @PathParam("email") String email) {
        usuario.setEmail(email);
        if (usuarioDAO.buscaByEmail(email) == null) {
            List<Map<String, Object>> mensajesError = new ArrayList<>();
            Map<String, Object> error = new HashMap<>();
            error.put("mensajeError", "No existe un usuario con email: " + email);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            usuarioDAO.modifica(usuario);
            return Response.ok(usuario).build();
        }
    }
}
