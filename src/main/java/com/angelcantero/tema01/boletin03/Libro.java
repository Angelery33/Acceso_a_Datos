package com.angelcantero.tema01.boletin03;

import java.util.Arrays;
import java.util.Objects;
/*<libro isbn="978-0134685991">
<titulo>Effective Java</titulo>
    <autor>
<nombre>Joshua Bloch</nombre>
      <nacimiento>1961-08-28</nacimiento>
    </autor>
    <anio>2018</anio>
    <generos>
      <genero>Tecnología</genero>
      <genero>Programación</genero>
    </generos>
    <disponible>true</disponible>
    <prestamos>
      <prestamo>
        <usuario>u123</usuario>
        <fecha>2024-10-10</fecha>
      </prestamo>
    </prestamos>
  </libro>*/

public class Libro {
    private String isbn;
    private String titulo;
    private Autor autor;
    private String anio;
    private Genero[] generos;
    private Prestamo[] prestamos;
    private boolean disponibilidad;


    public Libro(Prestamo[] prestamos, String isbn, String titulo, Autor autor, String anio, Genero[] generos,
                 boolean disponibilidad) {
        this.prestamos = prestamos;
        this.isbn = isbn;
        this.titulo = titulo;
        this.autor = autor;
        this.anio = anio;
        this.generos = generos;
        this.disponibilidad = disponibilidad;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public Autor getAutor() {
        return autor;
    }

    public void setAutor(Autor autor) {
        this.autor = autor;
    }

    public String getAnio() {
        return anio;
    }

    public void setAnio(String anio) {
        this.anio = anio;
    }

    public Genero[] getGeneros() {
        return generos;
    }

    public void setGeneros(Genero[] generos) {
        this.generos = generos;
    }

    public Prestamo[] getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(Prestamo[] prestamos) {
        this.prestamos = prestamos;
    }

    public boolean isDisponibilidad() {
        return disponibilidad;
    }

    public void setDisponibilidad(boolean disponibilidad) {
        this.disponibilidad = disponibilidad;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "isbn='" + isbn + '\'' +
                ", titulo='" + titulo + '\'' +
                ", autor=" + autor +
                ", anio=" + anio +
                ", generos=" + Arrays.toString(generos) +
                ", disponibilidad=" + disponibilidad +
                ", prestamos=" + Arrays.toString(prestamos) +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Libro libro = (Libro) o;
        return Objects.equals(isbn, libro.isbn);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(isbn);
    }


}








