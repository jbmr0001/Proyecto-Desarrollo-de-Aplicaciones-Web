package com.example.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Usuario {
    @Size(min=4,max=100, message="La longitud del correo debe estar entre {min} y {max} caracteres")
    @Pattern(regexp = ".*@.*", message = "El correo debe tener un formato válido (...@...")
    private String email;
    @NotBlank(message="Debe introducir una contraseña")
    private String contraseña;
    private String nombre;
    private String direccion;
    private String tarjeta;
    private String tipo;

    public Usuario(String email, String contraseña, String nombre, String direccion,String tarjeta,String tipo) {
        this.email = email;
        this.contraseña = contraseña;
        this.nombre=nombre;
        this.direccion=direccion;
        this.tarjeta=tarjeta;
        this.tipo=tipo;
    }

    public Usuario() {
        this.email = "";
        this.contraseña = "";
        this.nombre="";
        this.direccion="";
        this.tarjeta="";
        this.tipo="";
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

    public String getTipo() {
        return tipo;
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

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}

