package com.angelcantero.tema01.boletin02.ejercicio7;

import java.util.concurrent.BlockingQueue;

public class PrimoHilos implements Runnable {

    private final long startRange;
    private final long endRange;
    private final BlockingQueue<Long> primesQueue; // Cola compartida

    /**
     * Constructor del trabajador de primos.
     * @param startRange Inicio del rango de búsqueda (inclusivo).
     * @param endRange Fin del rango de búsqueda (inclusivo).
     * @param primesQueue La cola donde se depositarán los primos encontrados.
     */
    public PrimoHilos(long startRange, long endRange, BlockingQueue<Long> primesQueue) {
        this.startRange = startRange;
        this.endRange = endRange;
        this.primesQueue = primesQueue;
    }

    @Override
    public void run() {
        System.out.println("Thread " + Thread.currentThread().getName() + " iniciando búsqueda en el rango [" + startRange + " - " + endRange + "]");
        for (long number = startRange; number <= endRange; number++) {
            if (CalculadoraPrimo.esPrimo(number)) {
                try {
                    // Añade el primo encontrado a la cola.
                    // El método put() esperará si la cola está llena.
                    primesQueue.put(number);
                } catch (InterruptedException e) {
                    // Si el hilo es interrumpido, se detiene.
                    Thread.currentThread().interrupt();
                    break;
                }
            }
        }
        System.out.println("Thread " + Thread.currentThread().getName() + " ha finalizado su rango.");
    }
}
