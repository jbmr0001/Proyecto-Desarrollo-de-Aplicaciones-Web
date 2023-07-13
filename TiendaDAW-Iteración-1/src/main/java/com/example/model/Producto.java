package com.example.model;

public class Producto {
    private int id;
    private String nombre;
    private String descripcion;
    private int precio;

    public Producto(int id, String nombre,String descripcion, int precio) {
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
    }

    public Producto() {
        this.id = 0;
        this.nombre = "";
        this.descripcion="";
        this.precio=0;
    }

    public int getID() {
        return id;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public int getPrecio() {
        return precio;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }
}
