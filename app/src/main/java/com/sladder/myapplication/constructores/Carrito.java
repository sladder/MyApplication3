package com.sladder.myapplication.constructores;

import java.math.BigDecimal;

/**
 * Created by Andre on 18/07/2015.
 */
public class Carrito {
    private int id_carrito;
    private String nombre_carrito;
    private String fecha;
    private Double precio_total;

    public Carrito()
    {}
    public Carrito(int id_carrito, String nombre_carrito, String fecha, Double precio_total) {
        this.id_carrito = id_carrito;
        this.nombre_carrito = nombre_carrito;
        this.fecha = fecha;
        this.precio_total=precio_total;
    }
    public int getId_carrito() {
        return id_carrito;
    }
    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }
    public String getNombre_carrito() {
        return nombre_carrito;
    }
    public void setNombre_carrito(String nombre_carrito) {
        this.nombre_carrito = nombre_carrito;
    }
    public String getFecha() {
        return fecha;
    }
    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    public Double getPrecio_total() {
        return precio_total;
    }
    public void setPrecio_total (Double precio_total) {
        this.precio_total=precio_total;
    }
}
