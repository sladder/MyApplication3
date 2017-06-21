package com.sladder.myapplication.constructores;

import java.math.BigDecimal;

/**
 * Created by Andre on 18/07/2015.
 */
public class Detalle {
    private int id_detalle;
    private int id_producto;
    private int id_carrito;
    private int cantidad;
    private Double precio_cantidad;

    public Detalle()
    {}
    public Detalle(int id_detalle, int id_producto, int id_carrito, int cantidad, Double precio_cantidad) {
        this.id_detalle = id_detalle;
        this.id_producto = id_producto;
        this.id_carrito = id_carrito;
        this.cantidad = cantidad;
        this.precio_cantidad=precio_cantidad;
    }
    public int getId_detalle() {
        return id_detalle;
    }
    public void setId_detalle(int id_detalle) {
        this.id_detalle = id_detalle;
    }
    public int getId_producto() {
        return id_producto;
    }
    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }
    public int getId_carrito() {
        return id_carrito;
    }
    public void setId_carrito(int id_carrito) {
        this.id_carrito = id_carrito;
    }
    public int getCantidad() {
        return cantidad;
    }
    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }
    public Double getPrecio_cantidad() {
        return precio_cantidad;
    }
    public void setPrecio_cantidad(Double precio_cantidad) {
        this.precio_cantidad=precio_cantidad;
    }
}
