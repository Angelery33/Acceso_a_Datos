package com.angelcantero.tema01.boletin03;

import java.time.LocalDate;
import java.util.Objects;

public class Autor {
    private String nombre;
    private String fechaDeNacimiento;

    public Autor(String nombre, String fechaDeNacimiento) {
        this.nombre = nombre;
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public String toString() {
        return "Autor{" +
                "nombre='" + nombre + '\'' +
                ", fechaDeNacimiento='" + fechaDeNacimiento + '\'' +
                '}';
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFechaDeNacimiento() {
        return fechaDeNacimiento;
    }

    public void setFechaDeNacimiento(String fechaDeNacimiento) {
        this.fechaDeNacimiento = fechaDeNacimiento;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Autor autor = (Autor) o;
        return Objects.equals(nombre, autor.nombre) && Objects.equals(fechaDeNacimiento, autor.fechaDeNacimiento);
    }

    @Override
    public int hashCode() {
        return Objects.hash(nombre, fechaDeNacimiento);
    }
}
