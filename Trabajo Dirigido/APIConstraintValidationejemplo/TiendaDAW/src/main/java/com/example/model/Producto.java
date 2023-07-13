package com.example.model;

public class Producto {

    private int id;

    private String nombre;
    private String descripcion;
    private int precio;

    private String ruta;
    private int stock;

    public Producto(int id, String nombre,String descripcion, int precio,String ruta,int stock) {
        this.id=id;
        this.nombre=nombre;
        this.descripcion=descripcion;
        this.precio=precio;
        this.ruta=ruta;
        this.stock=stock;
    }

    public Producto() {
        this.id = 0;
        this.nombre = "ejemploejemplo";
        this.descripcion="ejemplo";
        this.precio=1;
        this.ruta="1.jpg";
        this.stock=1;
    }

    public int getId() {
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

    public String getRuta() {
        return ruta;
    }

    public void setRuta(String ruta) {
        this.ruta = ruta;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }


}
