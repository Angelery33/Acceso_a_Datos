package com.angelcantero.tema01.boletin03;

/*
 * Representa un inventario de productos en un almacén.
 */
public class Inventario {
    private String almacen;
    private String actualizado;
    private Producto[] productos;

    public Inventario() {
    }

    /*
     * Constructor con todos los parámetros.
     */
    public Inventario(String almacen, String actualizado, Producto[] productos) {
        this.almacen = almacen;
        this.actualizado = actualizado;
        this.productos = productos;
    }

    public String getAlmacen() {
        return almacen;
    }

    public void setAlmacen(String almacen) {
        this.almacen = almacen;
    }

    public String getActualizado() {
        return actualizado;
    }

    public void setActualizado(String actualizado) {
        this.actualizado = actualizado;
    }

    public Producto[] getProductos() {
        return productos;
    }

    public void setProductos(Producto[] productos) {
        this.productos = productos;
    }

    @Override
    public String toString() {
        return "Inventario{" + "almacen='" + almacen + '\'' + ", actualizado='" + actualizado + '\'' + ", productos=" + productos + '}';
    }
}
