package com.example.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

@Entity
public class Compra {
    @Id
    private int id;
    @NotBlank(message="Debe tener un email")
    @Size(min=4,max=100, message="La longitud del correo debe estar entre {min} y {max} caracteres")
    @Pattern(regexp = ".*@.*", message = "El correo debe tener un formato válido (...@...")
    private String email;
    @Min(value = 1, message = "Debe introducir un idProducto")
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
