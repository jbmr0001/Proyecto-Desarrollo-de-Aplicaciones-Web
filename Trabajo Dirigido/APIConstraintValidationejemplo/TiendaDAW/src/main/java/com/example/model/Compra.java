package com.example.model;

public class Compra {
    private int id;
    private String email;
    private int idProducto;

    public Compra() {
        this.email="prueba@prueba";
        this.idProducto=1;
        this.id=1;
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

