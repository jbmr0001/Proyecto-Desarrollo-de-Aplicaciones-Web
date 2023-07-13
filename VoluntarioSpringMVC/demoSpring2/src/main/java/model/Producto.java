package model;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import java.io.Serializable;
@Entity
public class Producto implements Serializable {

    @Id
    private int id;
    @Size(max=300, message="La longitud máxima debe ser de 300 caracteres")
    @NotBlank(message="Debe tener un nombre")
    private String nombre;
    private String descripcion;
    @Max(value = 500, message = "El precio máximo permitido es 500")
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
}
