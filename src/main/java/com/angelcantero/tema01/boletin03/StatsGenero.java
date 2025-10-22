package com.angelcantero.tema01.boletin03;

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
