package com.angelcantero.tema01.boletin03.ejercicio3;

import com.angelcantero.tema01.boletin03.*;

import java.util.HashMap;
import java.util.Map;



public class Ejercicio3 {
    public static void main(String[] args) {
    GenericParser parser = new GenericParser();
    Libro[] libros = null;

        try{
        libros = parser.parseLibro();

    }
        catch (Exception e){
        System.out.printf(e.getMessage());
    }
        for (Libro libro : libros) {
        System.out.println(libro);
    }
        System.out.println("\n--- Nombre de los libros ---");
        for (Libro libro : libros) {
            System.out.println(libro.getTitulo());
        }
        System.out.println("\n--- Estadísticas por Género ---");
        calcularYMostrarEstadisticas(libros);


}

    /*
     * Recibe el array de libros, calcula las estadísticas
     * y las muestra por pantalla.
     * @param libros El array de libros parseado del XML.
     */
    public static void calcularYMostrarEstadisticas(Libro[] libros) {

        HashMap<String, StatsGenero> statsLibrosGen = new HashMap<>();


        for (Libro lib : libros) {
            Genero[] gens = lib.getGeneros();
                for(int i=0;i<gens.length;i++){
                    String nombre = gens[i].getNombre();
                    StatsGenero stats = statsLibrosGen.computeIfAbsent(nombre, k -> new StatsGenero());
                    stats.agregarLibro();
                }





        }
        System.out.println("Nombre del género| Nº empleados |");
        System.out.println("---------------------------------");

        for (Map.Entry<String, StatsGenero> entry : statsLibrosGen.entrySet()) {
            String gens = entry.getKey();
            StatsGenero stats = entry.getValue();

            System.out.printf("%-17s | %-14d%n",
                    gens,
                    stats.getContadorDeLibros());
        }
    }
}
