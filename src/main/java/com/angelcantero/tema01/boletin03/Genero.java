package com.angelcantero.tema01.boletin03;

public class Genero {
    private String nombre;
    public Genero(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "Genero{" +
                "nombre='" + nombre + '\'' +
                '}';
    }
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
}
