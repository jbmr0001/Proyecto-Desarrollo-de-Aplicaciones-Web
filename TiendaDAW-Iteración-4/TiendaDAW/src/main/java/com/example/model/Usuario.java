package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
@Entity
public class Usuario {
    @Id
    @Size(min=4,max=100, message="La longitud del correo debe estar entre {min} y {max} caracteres")
    @Pattern(regexp = ".*@.*", message = "El correo debe tener un formato válido (...@...")
    private String email;
    @NotBlank(message="Debe introducir una contraseña")
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
        this.email = "";
        this.contraseña = "";
        this.nombre="";
        this.direccion="";
        this.tarjeta="";
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

