package com.angelcantero.tema01.boletin03;

/**
 * Clase auxiliar para guardar la suma total de salarios
 * y el número de empleados de un departamento.
 */
public class StatsGenero {
    private int contadorDeLibros;

    public StatsGenero() {
        this.contadorDeLibros = 0;
    }

    public void agregarLibro() {
        this.contadorDeLibros++;
    }

    public int getContadorDeLibros() {
        return this.contadorDeLibros;
    }
}
