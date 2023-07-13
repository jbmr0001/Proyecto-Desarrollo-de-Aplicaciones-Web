package com.example.model;

public class Usuario {
    private String email;
    private String contraseña;
    private String nombre;
    private String direccion;
    private String tarjeta;
    private boolean admin;

    public Usuario(String email, String contraseña, String nombre, String direccion,String tarjeta,boolean admin) {
        this.email = email;
        this.contraseña = contraseña;
        this.nombre=nombre;
        this.direccion=direccion;
        this.tarjeta=tarjeta;
        this.admin = admin;
    }

    public Usuario() {
        this.email = "ejemplo@ejemplo";
        this.contraseña = "ejemplo";
        this.nombre="ejemplo";
        this.direccion="ejemplo";
        this.tarjeta="111111111111";
        this.admin = false;
    }

    public String getEmail() {
        return email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public String getNombre() {
        return nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public String getTarjeta() {
        return tarjeta;
    }

    public boolean getAdmin() {
        return admin;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public void setTarjeta(String tarjeta) {
        this.tarjeta = tarjeta;
    }

    public void setAdmin(boolean tipo) {
        this.admin = tipo;
    }
}


