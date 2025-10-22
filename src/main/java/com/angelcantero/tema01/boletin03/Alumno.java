package com.angelcantero.tema01.boletin03;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Alumno {

    private String id;
    private String nombre;
    private boolean matriculado;
    private LocalDate fechaNacimiento;
    private Nota[] notas;
    private double notaMedia;


    /**
     * Constructor por defecto.
     * Inicializa la lista de notas como un ArrayList vacío.
     */
    public Alumno() {
    }

    /**
     * Constructor completo para crear un objeto Alumno con todos sus atributos.
     *
     * @param id El identificador único del alumno.
     * @param nombre El nombre completo del alumno.
     * @param matriculado Indica si el alumno está matriculado.
     * @param fechaNacimiento La fecha de nacimiento del alumno.
     * @param notas Una lista de objetos Nota que representan las calificaciones del alumno.
     */
    public Alumno(String id, String nombre, boolean matriculado, LocalDate fechaNacimiento,Nota[] notas) {
        this.id = id;
        this.nombre = nombre;
        this.matriculado = matriculado;
        this.fechaNacimiento = fechaNacimiento;
        // Se crea una nueva ArrayList para evitar problemas de referencia si la lista pasada es modificada externamente.
        this.notas = notas;
    }

    // --- Getters y Setters ---

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

    public boolean isMatriculado() {
        return matriculado;
    }

    public void setMatriculado(boolean matriculado) {
        this.matriculado = matriculado;
    }

    public double getNotaMedia() {
        return notaMedia;
    }

    public void setNotaMedia(double notaMedia) {
        this.notaMedia = notaMedia;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public Nota[] getNotas() {
        return notas;
    }

    public void setNotas(Nota[] notas) {
        this.notas = notas;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        return Objects.equals(id, alumno.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Alumno{" +
               "id='" + id + '\'' +
               ", nombre='" + nombre + '\'' +
               ", matriculado=" + matriculado +
               ", fechaNacimiento=" + fechaNacimiento +
               ", notas=" + notas +
               '}';
    }
}
