package com.angelcantero.tema01.boletin03;

import java.util.Objects;

public class Prestamo {
    private String idUsuario;
    private String fecha;

    public Prestamo(String idUsuario, String fecha) {
        this.idUsuario = idUsuario;
        this.fecha = fecha;
    }

    @Override
    public String toString() {
        return "Prestamo{" +
                "idUsuario='" + idUsuario + '\'' +
                ", fecha='" + fecha + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Prestamo prestamo = (Prestamo) o;
        return Objects.equals(idUsuario, prestamo.idUsuario) && Objects.equals(fecha, prestamo.fecha);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario, fecha);
    }
}
