package com.angelcantero.tema01.boletin03.ejercicio8;

import com.angelcantero.tema01.boletin03.GenericParser;
import com.angelcantero.tema01.boletin03.Genero;
import com.angelcantero.tema01.boletin03.Pelicula;
import com.angelcantero.tema01.boletin03.Videoteca;

import java.util.*;

public class Ejercicio8 {
    /*
     * Este metodo devuelve una lista de Strings con los generos de una videoteca ordenada alfabeticamente.
     *
     * @param un objeto Videoteca
     * @return una lista de Strings con los generos de una videoteca ordenada alfabeticamente
     */
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

    /*
     * Este metodo ordena en base a la nota media una lista de peliculas y devuelve una lista de peliculas ya
     * ordenada
     * @param un objeto Videoteca
     * @return una lista de peliculas ya ordenada
     */
    public static Pelicula[] calcularMedia(Videoteca videoteca) {
        Pelicula[] peliculas = videoteca.getPeliculas();
        Pelicula[] peliculasOrdenadas = new Pelicula[peliculas.length];
        System.arraycopy(peliculas, 0, peliculasOrdenadas, 0, peliculas.length);
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

        System.out.println("\nPeliculas de la Videoteca:\n");
        for (Pelicula pelicula : videoteca.getPeliculas()) {
            System.out.println(pelicula);
        }
        Pelicula[] pelOrd;
        pelOrd = calcularMedia(videoteca);
        for (Pelicula pelicula : pelOrd) {
            System.out.println(pelicula);
        }
        List<String> generos = mostrarGeneros(videoteca);
        System.out.println();
        System.out.println("Generos ordenados alfabeticamente:");

        for (String genero : generos) {
            System.out.println(genero);
        }

    }

}
