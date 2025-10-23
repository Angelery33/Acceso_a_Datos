package com.angelcantero.tema01.boletin03.ejercicio8;

import com.angelcantero.tema01.boletin03.*;

import java.util.*;

public class Ejercicio8 {

    public static List<String> mostrarGeneros(Videoteca videoteca) {
        Pelicula[] peliculas = videoteca.getPeliculas();
        Set<String> generosSet = new HashSet<>();

        for (Pelicula pelicula : peliculas) {
            if (pelicula.getGeneros() != null) {
                for (Genero genero : pelicula.getGeneros()) {
                    if (genero != null) {
                        generosSet.add(genero.getNombre());
                    }
                }
            }
        }
        List<String> generosOrdenados = new ArrayList<>(generosSet);
        Collections.sort(generosOrdenados);
        return generosOrdenados;
    }
        public static Pelicula[] calcularMedia(Videoteca videoteca) {
            Pelicula[] peliculas = videoteca.getPeliculas();
            Pelicula[] peliculasOrdenadas = new Pelicula[peliculas.length];
            for (int i = 0; i < peliculas.length; i++) {
                peliculasOrdenadas[i] = peliculas[i];
            }
            /*peliculasOrdenadas[0]=peliculas[0]*/
            ;
            for (int i = 0; i < peliculasOrdenadas.length - 1; i++) {
                for (int j = i + 1; j < peliculasOrdenadas.length; j++) {
                    if (peliculasOrdenadas[j].getNotaMedia() > peliculasOrdenadas[i].getNotaMedia()) {
                        // Intercambiamos las posiciones
                        Pelicula temp = peliculasOrdenadas[i];
                        peliculasOrdenadas[i] = peliculasOrdenadas[j];
                        peliculasOrdenadas[j] = temp;
                    }
                }
            }
            return peliculasOrdenadas;
        }
    public static void main(String[] args) {
        Scanner lector = new Scanner(System.in);
        GenericParser parser = new GenericParser();
        Videoteca videoteca = null;
        try {
            videoteca = parser.parseVideoteca();

        } catch (Exception e) {
            System.out.printf(e.getMessage());
        }
        System.out.println("Videoteca:");
        for (Pelicula pelicula : videoteca.getPeliculas()){
            System.out.println(pelicula);
    }
        Pelicula[] pelOrd;
        pelOrd=calcularMedia(videoteca);
        for (Pelicula pelicula : pelOrd) {
            System.out.println(pelicula);
        }
        List<String> generos = mostrarGeneros(videoteca);
        System.out.println("Generos:");

        for (String genero : generos) {
            System.out.println(genero);
        }

    }

}
