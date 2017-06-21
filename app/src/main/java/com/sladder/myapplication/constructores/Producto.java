package com.sladder.myapplication.constructores;

import java.math.BigDecimal;

/**
 * Created by Andre on 18/07/2015.
 */
public class Producto{
    private int id_producto;
    private String nombre_producto;
    private Double precio_u;

    public Producto()
    {}
    public Producto (int id_producto, String nombre_producto, Double precio_u) {
        this.id_producto = id_producto;
        this.nombre_producto = nombre_producto;
        this.precio_u = precio_u;
    }
    public int getId_producto() {
        return id_producto;
    }
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public String getNombre_producto() {
        return nombre_producto;
    }
    public void setNombre_producto(String nombre_producto) {
        this.nombre_producto = nombre_producto;
    }
    public Double getPrecio_u() {
        return precio_u;
    }
    public void setPrecio_u(Double precio_u) {
        this.precio_u = precio_u;
    }
}
