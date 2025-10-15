package com.angelcantero.tema01.boletin01.ejercicio7;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicLong;


public class Main {
    private static final long RANGO_POR_HILO = 1_000_000L;

    public static void main(String[] args) {
        // 1. Determinar el número de hilos a usar basado en los núcleos de la CPU.
        int cores = Runtime.getRuntime().availableProcessors();
        System.out.println("Detectados " + cores + " núcleos. Creando un pool de hilos de ese tamaño.");

        // ExecutorService es la forma moderna de gestionar hilos en Java.
        ExecutorService executor = Executors.newFixedThreadPool(cores);

        // Cola bloqueante para comunicar los primos encontrados de los trabajadores al escritor.
        BlockingQueue<Long> primesQueue = new LinkedBlockingQueue<>();

        // 2. Obtener el último número primo para saber por dónde empezar.
        AtomicLong numeroActual = new AtomicLong(2); // Usamos AtomicLong para seguridad entre hilos.
        try {
            long primerNumero = CalculadoraPrimo.conseguirPrimerNumero();
            numeroActual.set(primerNumero);
            System.out.println("Continuando la búsqueda desde el número: " + primerNumero);
        } catch (IOException | NumberFormatException e) {
            System.err.println("No se pudo leer el último primo. Empezando desde 2. Error: " + e.getMessage());
        }

        // 3. Crear y lanzar el hilo escritor (Consumidor).
        // Este será el ÚNICO hilo que escriba en el fichero.
        Thread fileWriterThread = new Thread(() -> {
            try (BufferedWriter writer = new BufferedWriter(new FileWriter(CalculadoraPrimo.FILENAME, true))) {
                while (true) {
                    // El método take() espera hasta que haya un elemento disponible en la cola.
                    long prime = primesQueue.take();
                    writer.write(String.valueOf(prime));
                    writer.newLine();
                    writer.flush(); // Aseguramos que se escriba inmediatamente.
                    System.out.println("✍️ Primo guardado en fichero: " + prime);
                }
            } catch (IOException e) {
                System.err.println("Error fatal al escribir en el fichero: " + e.getMessage());
            } catch (InterruptedException e) {
                System.out.println("Hilo escritor interrumpido. Finalizando.");
                Thread.currentThread().interrupt();
            }
        });
        fileWriterThread.start(); // Iniciamos el hilo escritor.

        // 4. Bucle principal para asignar trabajo a los hilos (Productores).
        // Este bucle crea tareas y las envía al executor para que las procese.
        while (true) {
            long startRange = numeroActual.get();
            long endRange = startRange + RANGO_POR_HILO - 1;

            // Creamos un nuevo trabajador con el rango calculado
            Runnable worker = new PrimoHilos(startRange, endRange, primesQueue);

            // El executor se encargará de asignarlo a un hilo libre del pool.
            executor.execute(worker);

            // Preparamos el inicio del siguiente rango.
            numeroActual.set(endRange + 1);

            // Pequeña pausa para no saturar la asignación de tareas.
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                break;
            }
        }
    }
}
