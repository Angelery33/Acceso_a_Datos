package com.angelcantero.tema01.boletin03;

import java.util.List;

/**
 * Representa un producto en el inventario.
 */
public class Producto {
    private String id;
    private String nombre;
    private int stock;
    private double precio;
    private List<String> tags;
    private Ubicacion ubicacion;

    /**
     * Constructor por defecto.
     */
    public Producto() {
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param id        El identificador único del producto.
     * @param nombre    El nombre del producto.
     * @param stock     La cantidad de unidades en stock.
     * @param precio    El precio del producto.
     * @param tags      Una lista de etiquetas asociadas al producto.
     * @param ubicacion El objeto Ubicacion que describe dónde se encuentra el producto.
     */
    public Producto(String id, String nombre, int stock, double precio, List<String> tags, Ubicacion ubicacion) {
        this.id = id;
        this.nombre = nombre;
        this.stock = stock;
        this.precio = precio;
        this.tags = tags;
        this.ubicacion = ubicacion;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public List<String> getTags() {
        return tags;
    }

    public void setTags(List<String> tags) {
        this.tags = tags;
    }

    public Ubicacion getUbicacion() {
        return ubicacion;
    }

    public void setUbicacion(Ubicacion ubicacion) {
        this.ubicacion = ubicacion;
    }

    @Override
    public String toString() {
        return "Producto{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", stock=" + stock +
                ", precio=" + precio +
                ", tags=" + tags +
                ", ubicacion=" + ubicacion +
                '}';
    }
}
