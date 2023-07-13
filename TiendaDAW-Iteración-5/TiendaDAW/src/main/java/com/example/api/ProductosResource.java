package com.example.api;

import com.example.dao.ProductoDAO;
import com.example.model.Producto;
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

@Path("/productos")
@RequestScoped //Ojo, jakarta.enterprise.context.RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ProductosResource {

    @Inject
    @DAOJpa
    private ProductoDAO productoDAO;

    @GET
    public List<Producto> listadoProductos() {
        List<Producto> lista= productoDAO.buscaTodos();
        return lista;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearProducto(@Valid Producto producto ) {
        if(productoDAO.buscaById(producto.getId())!=null){
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "Ya existe un producto con id: "+producto.getId());
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        }else{
            productoDAO.guarda(producto);
            return Response.ok(producto).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response mostrarProducto(@PathParam("id") Integer id) {
        if(productoDAO.buscaById(id)==null) {
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningún producto con id: "+id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            Producto producto=productoDAO.buscaById(id);
            return Response.ok(producto).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response borrarProducto(@PathParam("id") Integer id) {
        Producto producto=new Producto();
        producto.setId(id);
        if (productoDAO.borra(producto)!=true) {

            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningún usuario con id: "+id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            return Response.ok(id).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarProducto(@Valid Producto producto, @PathParam("id") Integer id) {
        producto.setId(id);
        if (productoDAO.buscaById(id) == null) {
            List<Map<String, Object>> mensajesError = new ArrayList<>();
            Map<String, Object> error = new HashMap<>();
            error.put("mensajeError", "No existe un producto con id: " + id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            productoDAO.modifica(producto);
            return Response.ok(producto).build();
        }
    }
}
