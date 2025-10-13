package com.angelcantero.tema01.boletin01.ejercicio6;

import java.io.IOException;

import static com.angelcantero.tema01.boletin01.ejercicio6.CalculadoraPrimo.*;




public class MainPrimos {
    /**
     * Método principal que inicia la ejecución del programa.
     * @param args Argumentos de la línea de comandos (no utilizados en este programa).
     */
    public static void main(String[] args) throws IOException {

        long startNumber = conseguirPrimerNumero(); // Determina el número desde el cual comenzar a buscar primos
        if (startNumber == 2) {
            System.out.println("El archivo no existe. Comenzando desde 2.");
        }
        System.out.println("Iniciando cálculo de números primos desde: " + startNumber);

        // Bucle infinito para buscar y almacenar primos continuamente
        while (true) {
            if (esPrimo(startNumber)) {
                añadirPrimoAlArchivo(startNumber); // Si es primo, lo añade al archivo
                System.out.println("✅ Nuevo primo encontrado y guardado: " + startNumber);
            }
            // Avanza al siguiente número para verificar.
            // Si el número actual es 2, el siguiente es 3. Para el resto, salta los números pares.
            if (startNumber == 2) {
                startNumber++;
            } else {
                startNumber += 2; // Incrementa en 2 para verificar solo números impares después del 2
            }
        }
    }
}
