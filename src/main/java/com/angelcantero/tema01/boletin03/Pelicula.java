package com.angelcantero.tema01.boletin03;

import java.util.List;
import java.util.Objects;

public class Pelicula {
    private String id;
    private String titulo;
    private String director;
    private int estreno;
    private int duracionMin;
    private Genero[] generos;
    private Puntuaciones puntuaciones;
    private double notaMedia;

    public Pelicula() {
    }

    public Pelicula(String id, String titulo, String director, int estreno, int duracionMin, Genero[] generos,
                    Puntuaciones puntuaciones) {
        this.id = id;
        this.titulo = titulo;
        this.director = director;
        this.estreno = estreno;
        this.duracionMin = duracionMin;
        this.generos = generos;
        this.puntuaciones = puntuaciones;
        this.notaMedia=calcularMedia();
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDirector() {
        return director;
    }

    public void setDirector(String director) {
        this.director = director;
    }

    public int getEstreno() {
        return estreno;
    }

    public void setEstreno(int estreno) {
        this.estreno = estreno;
    }

    public int getDuracionMin() {
        return duracionMin;
    }

    public void setDuracionMin(int duracionMin) {
        this.duracionMin = duracionMin;
    }

    public Genero[] getGeneros() {
        return generos;
    }

    public void setGeneros(Genero[] generos) {
        this.generos = generos;
    }

    public Puntuaciones getPuntuaciones() {
        return puntuaciones;
    }

    public void setPuntuaciones(Puntuaciones puntuaciones) {
        this.puntuaciones = puntuaciones;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public double calcularMedia() {

            Puntuaciones punt = this.getPuntuaciones();
            return ((punt.getImdb() / 10) + punt.getRt()) / 2;


    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pelicula pelicula = (Pelicula) o;
        return estreno == pelicula.estreno && duracionMin == pelicula.duracionMin && Objects.equals(id, pelicula.id) && Objects.equals(titulo, pelicula.titulo) && Objects.equals(director, pelicula.director) && Objects.equals(generos, pelicula.generos) && Objects.equals(puntuaciones, pelicula.puntuaciones);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, titulo, director, estreno, duracionMin, generos, puntuaciones);
    }

    @Override
    public String toString() {
        return "Pelicula{" +
               "id='" + id  +
               ", titulo='" + titulo +
               ", director='" + director +
               ", estreno=" + estreno +
               ", duracionMin=" + duracionMin +
               ", generos=" + generos +
               ", puntuaciones=" + puntuaciones +
                ", puntuación media ="+notaMedia+
               '}';
    }
}
