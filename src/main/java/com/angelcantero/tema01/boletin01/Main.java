package com.angelcantero.tema01.boletin01;

import java.io.*;
import java.security.NoSuchAlgorithmException;
import java.time.LocalDate;


public class Main {
    public static void main(String[] args) {
        // --- Pruebas Ejercicio 1 ---
        // Ejercicio1 e1 = new Ejercicio1();
        // try {
        //     e1.procesarDNIs(new File("C:\\Users\\angel\\Desktop\\Clase\\Acceso_a_Datos\\Tema1\\tema01\\ficheros\\dnis.txt"));
        // } catch (IOException e) {
        //     System.out.println(e.getMessage());
        // }

        // --- Pruebas Ejercicio 2 ---
        try {

            String rutaFicheroAlumnos = "C:\\Users\\angel\\Desktop\\Clase\\Acceso_a_Datos\\Tema1\\tema01\\ficheros\\alumnos.csv";
            GestionAlumnos gestion = new GestionAlumnos(rutaFicheroAlumnos);

            // Mostramos el estado inicial del fichero
            System.out.println("Estado inicial del fichero:");
            System.out.println(gestion);
            System.out.println("\n");

            // Creamos un array de alumnos para insertar
            Alumno[] nuevosAlumnos = {
                    new Alumno("101", "Ana", "García", "López", LocalDate.of(2002, 5, 15)),
                    new Alumno("102", "Luis", "Martínez", "Sánchez", LocalDate.of(2001, 11, 22)),
                    new Alumno("103", "Elena", "Ruiz", "Jiménez", LocalDate.of(2002, 3, 10))
            };

            System.out.println("-> Insertando 3 alumnos...");
            gestion.insertarAlumnos(nuevosAlumnos);
            System.out.println(gestion);
            System.out.println("\n");

            System.out.println("-> Eliminando al alumno con NIA 102...");
            gestion.eliminarAlumno(new Alumno("102", "", "", "", null)); // Solo necesitamos el NIA para la eliminación
            System.out.println(gestion);

        } catch (IOException e) {
            System.err.println("Ha ocurrido un error de E/S: " + e.getMessage());
            e.printStackTrace();
        }
    }


}
