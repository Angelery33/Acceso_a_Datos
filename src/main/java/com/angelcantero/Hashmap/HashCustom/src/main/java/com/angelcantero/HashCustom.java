package com.angelcantero;

import java.util.LinkedList;

/**
 * Implementación personalizada y simplificada de un HashMap.
 *
 * <p>Este  HashCustom permite almacenar pares clave-valor,
 * calcular el índice mediante el hash de la clave, y manejar
 * colisiones usando listas enlazadas.</p>
 *
 * @param <K> el tipo de las claves
 * @param <V> el tipo de los valores
 */
public class HashCustom<K, V> {

    /** Tabla de buckets, cada uno es una lista enlazada de entradas. */
    private LinkedList<Entrada<K, V>>[] tabla;

    /** Capacidad fija de la tabla (número de buckets). */
    private int capacidad = 256;

    /**
     * Crea un nuevo  con la capacidad por defecto (16).
     */

    public HashCustom() {
        tabla = new LinkedList[capacidad];
    }

    /**
     * Clase interna que representa un par clave-valor.
     */
    private static class Entrada<K, V> {
        K key;
        V value;

        Entrada(K key, V value) {
            this.key = key;
            this.value = value;
        }
    }

    /**
     * Calcula el índice de la tabla donde debería ir la clave dada.
     *
     * @param key la clave cuyo índice se quiere calcular
     * @return el índice en el rango {@code [0, capacidad)}
     */
    private int calcularIndice(K key) {
        int hash = key.hashCode();
        return Math.abs(hash) % capacidad;
    }

    /**
     * Inserta un par clave-valor en el mapa.
     * <p>
     * - Si la clave no existe, se añade una nueva entrada. <br>
     * - Si la clave ya existe, se actualiza el valor asociado.
     * </p>
     *
     * @param key   la clave a insertar o actualizar
     * @param value el valor de la clave
     */
    public void put(K key, V value) {
        int indice = calcularIndice(key);

        if (tabla[indice] == null) {
            tabla[indice] = new LinkedList<>();
        }

        for (Entrada<K, V> entry : tabla[indice]) {
            if (entry.key.equals(key)) {
                entry.value = value; // actualizamos
                return;
            }
        }

        tabla[indice].add(new Entrada<>(key, value)); // nueva clave
    }

    /**
     * Obtiene el valor asociado a una clave.
     *
     * @param key la clave a buscar
     * @return el valor asociado, o {@code null} si la clave no existe
     */
    public V get(K key) {
        int indice = calcularIndice(key);

        if (tabla[indice] == null) {
            return null;
        }

        for (Entrada<K, V> entry : tabla[indice]) {
            if (entry.key.equals(key)) {
                return entry.value;
            }
        }

        return null;
    }

    /**
     * Elimina la clave dada y su valor asociado.
     *
     * @param key la clave a eliminar
     */
    public void remove(K key) {
        int indice = calcularIndice(key);

        if (tabla[indice] != null) {
            tabla[indice].removeIf(entry -> entry.key.equals(key));
        }
    }
}

