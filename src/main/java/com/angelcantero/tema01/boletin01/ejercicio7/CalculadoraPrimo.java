package com.angelcantero.tema01.boletin01.ejercicio7;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class CalculadoraPrimo {

    public static final String FILENAME = "primos.txt"; // AHORA ES PÚBLICO

    public CalculadoraPrimo() {}

    public static long conseguirPrimerNumero() throws IOException, NumberFormatException {
        // ... (código idéntico al de la respuesta anterior)
        File file = new File(FILENAME);
        if (!file.exists()) {
            return 2;
        }
        String lastLine = "";
        long lastPrime = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String currentLine;
            while ((currentLine = reader.readLine()) != null) {
                lastLine = currentLine;
            }
            if (!lastLine.isEmpty()) {
                lastPrime = Long.parseLong(lastLine);
            }
        }
        if (lastPrime > 0) {
            return (lastPrime == 2) ? 3 : lastPrime + 2;
        } else {
            return 2;
        }
    }

    public static boolean esPrimo(long number) {
        // ... (código idéntico al de la respuesta anterior)
        if (number <= 1) return false;
        if (number == 2) return true;
        if (number % 2 == 0) return false;
        for (long i = 3; i <= Math.sqrt(number); i += 2) {
            if (number % i == 0) return false;
        }
        return true;
    }
}
