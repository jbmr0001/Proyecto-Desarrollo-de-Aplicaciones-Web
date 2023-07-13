package com.example.tiendadaw;

import jakarta.ws.rs.ext.Provider;
import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerResponseContext;
import jakarta.ws.rs.container.ContainerResponseFilter;

@Provider
public class CORSAPIFilter implements ContainerResponseFilter {

    @Override
    public void filter(ContainerRequestContext requestContext, ContainerResponseContext response) {
        //response.getHeaders().putSingle("Access-Control-Max-Age",10);
        //Change origin with allowed client hostname
        //response.getHeaders().putSingle("Access-Control-Allow-Origin", "http://www.ujaen.es:8080");
        response.getHeaders().putSingle("Access-Control-Allow-Origin", "*"); //Allow any origin (INSECURE)
        response.getHeaders().putSingle("Access-Control-Allow-Methods", "GET, POST, PUT, DELETE, OPTIONS");
        response.getHeaders().putSingle("Access-Control-Allow-Headers", "Content-Type");
    }

}
