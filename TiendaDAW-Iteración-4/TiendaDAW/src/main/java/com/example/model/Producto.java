package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
public class Producto {

    @Id
    private int id;
    @Size(max=300, message="La longitud máxima debe ser de 300 caracteres")
    @NotBlank(message="Debe tener un nombre")
    private String nombre;
    private String descripcion;
    @Max(value = 500, message = "El precio máximo permitido es 500")
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
        this.nombre = "";
        this.descripcion="";
        this.precio=0;
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

    public void decrementaStock(){
        this.stock=this.stock-1;
    }

    public boolean hayStock(){
        return this.stock!=0;
    }
}
