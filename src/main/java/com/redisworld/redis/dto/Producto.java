package com.redisworld.redis.dto;

import javax.persistence.*;
import java.io.Serializable;

@Entity
public class Producto implements Serializable {

    public Producto(){

    }
    public Producto(int id, String nombre){
        this.id = id;
        this.nombre = nombre;
    }

    private static final long serialVersionUID = 7156526077883281623L;

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private int id;
    private String nombre;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id=" + id +
                ", nombre='" + nombre + '\'' +
                '}';
    }
}
