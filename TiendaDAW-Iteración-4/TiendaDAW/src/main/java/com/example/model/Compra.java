package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class Compra {
    @Id
    private int id;
    @NotBlank(message="Debe tener un email")
    private String email;
    private int idProducto;

    public Compra() {
        this.email="";
        this.idProducto=0;
        this.id=0;
    }

    public Compra(int id,String _email, int idProducto) {
        this.id=id;
        this.email=_email;
        this.idProducto=idProducto;
    }

    public String getEmail(){
        return email;
    }

    public int getIdProducto(){
        return idProducto;
    }

    public void setEmail(String _email){
        email=_email;
    }

    public void setIdProducto(int _idProducto){
        idProducto=_idProducto;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
