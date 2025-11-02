package com.angelcantero.tema01.boletin03;

import java.util.Objects;

public class Videoteca {
    private Pelicula[] peliculas;

    public Videoteca() {
    }

    public Videoteca(Pelicula[] peliculas) {
        this.peliculas = peliculas;
    }

    public Pelicula[] getPeliculas() {
        return peliculas;
    }

    public void setPeliculas(Pelicula[] peliculas) {
        this.peliculas = peliculas;
    }

   /* @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Videoteca videoteca = (Videoteca) o;
        return Objects.equals(peliculas, videoteca.peliculas);
    }

    @Override
    public int hashCode() {
        return Objects.hash(peliculas);
    }*/

    @Override
    public String toString() {
        return "Videoteca{" + "peliculas=" + peliculas + '}';
    }
}
