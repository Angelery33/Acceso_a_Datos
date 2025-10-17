package com.angelcantero.tema01.boletin02;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Objects;

public class Alumno {
    private final String nia;
    private final String nombre;
    private final String apellido1;
    private final String apellido2;
    private final LocalDate fechaNacimiento;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ISO_LOCAL_DATE; // yyyy-MM-dd

    public Alumno(String nia, String nombre, String apellido1, String apellido2, LocalDate fechaNacimiento) {
        this.nia = nia;
        this.nombre = nombre;
        this.apellido1 = apellido1;
        this.apellido2 = apellido2;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters
    public String getNia() { return nia; }
    public String getNombre() { return nombre; }
    public String getApellido1() { return apellido1; }
    public String getApellido2() { return apellido2; }
    public LocalDate getFechaNacimiento() { return fechaNacimiento; }

    /**
     * Convierte el objeto Alumno a una cadena en formato CSV.
     * @return String en formato "nia,nombre,apellido1,apellido2,fechaNacimiento"
     */
    public String toCsv() {
        return String.join(",",
                nia,
                nombre,
                apellido1,
                apellido2,
                fechaNacimiento.format(DATE_FORMATTER)
        );
    }

    /**
     * Crea un objeto Alumno a partir de una cadena en formato CSV.
     * @param csvLine La línea de texto del fichero.
     * @return Un nuevo objeto Alumno.
     * @throws IllegalArgumentException si el formato de la línea no es válido.
     */
    public static Alumno fromCsv(String csvLine) {
        String[] parts = csvLine.split(",");
        if (parts.length != 5) {
            throw new IllegalArgumentException("La línea CSV no tiene el formato correcto: " + csvLine);
        }
        return new Alumno(parts[0], parts[1], parts[2], parts[3], LocalDate.parse(parts[4], DATE_FORMATTER));
    }

    @Override
    public String toString() {
        return String.format("Alumno [NIA: %s, Nombre: %s %s %s, F. Nac: %s]",
                nia, nombre, apellido1, apellido2, fechaNacimiento.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alumno alumno = (Alumno) o;
        // Se considera que dos alumnos son iguales si su NIA es el mismo.
        return Objects.equals(nia, alumno.nia);
    }

    @Override
    public int hashCode() {
        // Se basa en el NIA para la consistencia con equals().
        return Objects.hash(nia);
    }
}
