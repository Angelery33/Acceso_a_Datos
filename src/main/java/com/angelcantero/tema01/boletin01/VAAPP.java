package com.angelcantero.tema01.boletin01;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

public class VAAPP {
    private final static Scanner sc = new Scanner(System.in);
    /**
     * Método principal que inicia el programa.
     * Muestra el menú principal y gestiona las opciones del usuario.
     *
     * @param args argumentos de línea de comandos (no se usan)
     */
    public static void main(String[] args) {
        ValidarAcceso va = new ValidarAcceso();

        int opcion;
        try {
            do {
                System.out.println("**********************");
                System.out.println("1. Validar acceso");
                System.out.println("2. Salir");
                System.out.println("**********************");
                System.out.print("Elige opción: ");
                opcion = sc.nextInt();
                sc.nextLine(); // limpiar buffer

                switch (opcion) {
                    case 1:
                        System.out.print("Introduce la contraseña: ");
                        String psw = sc.nextLine();

                        String hashGuardado = va.cargarHash();
                        if (va.validarAcceso(psw, hashGuardado)) {
                            System.out.println("Acceso válido");
                            mostrarMenuInterno(va);
                        } else {
                            System.out.println("Acceso denegado");
                        }
                        break;

                    case 2:
                        System.out.println("Saliendo...");
                        break;

                    default:
                        System.out.println("Opción no válida");
                }
            } while (opcion != 2);

            sc.close();
        } catch (NoSuchAlgorithmException | IOException | RuntimeException e) {
            System.out.println("Error: " + e.getMessage());

        }
    }

    /**
     * Muestra menú interno tras validar el acceso correctamente.
     * Permite modificar la contraseña o salir al menú principal.
     *
     * @param va objeto para gestionar contraseñas
     */
    private static void mostrarMenuInterno( ValidarAcceso va) throws NoSuchAlgorithmException, IOException {
        int opcion;
        do {
            System.out.println("*************************");
            System.out.println("1. Modificar contraseña");
            System.out.println("2. Salir");
            System.out.println("*************************");
            System.out.print("Elige opción: ");
            opcion = sc.nextInt();
            sc.nextLine();

            switch (opcion) {
                case 1:
                    System.out.print("Introduce la contraseña anterior: ");
                    String oldPsw = sc.nextLine();

                    String hashGuardado = va.cargarHash();
                    if (va.validarAcceso(oldPsw, hashGuardado)) {
                        System.out.print("Introduce la nueva contraseña: ");
                        String newPsw = sc.nextLine();


                            String newHash = va.getHashSHA1(newPsw);
                            va.guardarHash(newHash);
                            System.out.println("Contraseña cambiada con éxito");

                    } else {
                        System.out.println("Contraseña anterior incorrecta");
                    }
                    break;

                case 2:
                    System.out.println("Volviendo al menú principal...");
                    break;

                default:
                    System.out.println("Opción no válida");
            }

        } while (opcion != 2);

    }


}
