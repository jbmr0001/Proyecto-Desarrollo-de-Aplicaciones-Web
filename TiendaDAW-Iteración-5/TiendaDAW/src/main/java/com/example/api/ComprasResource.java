package com.example.api;

import com.example.dao.CompraDAO;
import com.example.model.Compra;
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

@Path("/compras")
@RequestScoped //Ojo, jakarta.enterprise.context.RequestScoped
@Produces(MediaType.APPLICATION_JSON)
public class ComprasResource {

    @Inject
    @DAOJpa
    private CompraDAO compraDAO;

    @GET
    public List<Compra> listadoCompras() {
        List<Compra> lista= compraDAO.buscaTodos();
        return lista;
    }
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    public Response crearCompra(@Valid Compra compra ) {
        if(compraDAO.buscaById(compra.getId())!=null){
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "Ya existe un compra con id: "+compra.getId());
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        }else{
            compraDAO.guarda(compra);
            return Response.ok(compra).build();
        }
    }

    @GET
    @Path("/{id}")
    public Response mostrarComprao(@PathParam("id") Integer id) {
        if(compraDAO.buscaById(id)==null) {
            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningúna compra con id: "+id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            Compra compra=compraDAO.buscaById(id);
            return Response.ok(compra).build();
        }
    }

    @PUT
    @Path("/{id}")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response modificarCompra(@Valid Compra compra, @PathParam("id") Integer id) {
        compra.setId(id);
        if (compraDAO.buscaById(id) == null) {
            List<Map<String, Object>> mensajesError = new ArrayList<>();
            Map<String, Object> error = new HashMap<>();
            error.put("mensajeError", "No existe una compra con id: " + id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            compraDAO.modifica(compra);
            return Response.ok(compra).build();
        }
    }

    @DELETE
    @Path("/{id}")
    public Response borrarProducto(@PathParam("id") Integer id) {
        Compra compra=new Compra();
        compra.setId(id);
        if (compraDAO.borra(compra)!=true) {

            List<Map<String,Object>> mensajesError=new ArrayList<>();
            Map<String,Object> error=new HashMap<>();
            error.put("mensajeError", "No existe ningúna compra con id: "+id);
            mensajesError.add(error);
            return Response.status(Response.Status.BAD_REQUEST)
                    .entity(mensajesError).build();
        } else {
            return Response.ok(id).build();
        }
    }
}