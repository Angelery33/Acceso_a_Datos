package com.angelcantero.tema01.boletin02.ejercicio6;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.File;

/**
 * Clase que contiene la lógica para calcular números primos y gestionarlos
 */
public class CalculadoraPrimo {

    private static final String FILENAME = "primos.txt"; // Nombre del archivo para almacenar los primos
    /*private static final String RUTA= CalculadoraPrimo.class.getResource("primos.txt");*/

    /**
     * Determina el número desde el cual el programa debe empezar a buscar primos.
     * Si el archivo de primos existe, lee el último primo guardado y retorna el siguiente número.
     * Si el archivo no existe o está vacío, retorna 2 (el primer número primo).
     *
     * @return El número desde el cual el cálculo de primos debe continuar.
     * @throws IOException Si ocurre un error de entrada/salida al leer el archivo.
     * @throws NumberFormatException Si el contenido del archivo no es un número válido.
     */
    public static long conseguirPrimerNumero() throws IOException, NumberFormatException {
        File file = new File(FILENAME);

        // Si el archivo no existe, simplemente retornamos 2.
        if (!file.exists()) {
            return 2;
        }

        String lastLine = "";
        long lastPrime = 0;

        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine; // Guarda la última línea leída
            }

            // Si se encontró una última línea no vacía, intenta parsearla.
            // Si el contenido no es un número válido, NumberFormatException se lanzará.
            if (!lastLine.isEmpty()) {
                lastPrime = Long.parseLong(lastLine);
            }
        }
        // Si hay un IOException al abrir o leer el archivo, se lanzará desde aquí.

        // Después de intentar leer el archivo, determina el siguiente número.
        if (lastPrime > 0) {
            // Si el último primo fue 2, el siguiente número a probar es 3.
            // De lo contrario, saltamos al siguiente impar para continuar la búsqueda.
            return (lastPrime == 2) ? 3 : lastPrime + 2;
        } else {
            // Esto cubrirá casos donde el archivo existía pero estaba vacío (lastPrime es 0).
            // La decisión de informar o no se hará en el Controlador/Vista.
            return 2;
        }
    }

    /**
     * Comprueba si un número dado es primo.
     * Un número primo es un número natural mayor que 1 que no tiene divisores positivos
     * aparte de 1 y el propio número.
     *
     * @param number El número a verificar.
     * @return {@code true} si el número es primo, {@code false} en caso contrario.
     */
    public static boolean esPrimo(long number) {
        if (number <= 1) {
            return false; // Los números menores o iguales a 1 no son primos
        }
        if (number == 2) {
            return true; // 2 es el único número primo par
        }
        if (number % 2 == 0) {
            return false; // Todos los demás números pares no son primos
        }
        // Solo necesitamos verificar divisores impares hasta la raíz cuadrada del número
        for (long i = 3; i <= Math.sqrt(number); i +=2) {
            if (number % i == 0) {
                return false; // Si tiene un divisor, no es primo
            }
        }
        return true; // Si no se encontraron divisores, es primo
    }

    /**
     * Añade un número primo al final del archivo especificado por FILENAME.
     *
     * @param prime El número primo a escribir en el archivo.
     * @throws IOException Si ocurre un error de entrada/salida al escribir en el archivo.
     */
    public static void añadirPrimoAlArchivo(long prime) throws IOException {
        // El true en FileWriter indica que se añadirá al final del archivo (append).
        // Si hay un IOException, se lanzará desde aquí.
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILENAME, true))) {
            writer.write(String.valueOf(prime)); // Escribe el primo
            writer.newLine(); // Añade un salto de línea para el siguiente primo
        }
        // No hay bloque catch aquí, por lo que la IOException se propagará.
    }
}
