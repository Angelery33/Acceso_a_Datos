package com.angelcantero.tema01.boletin02;

import java.io.*;


public class Ejercicio1 {


    public void procesarDNIs(File archivoEntrada) throws IOException {
        File archivoSalida = generarNombreArchivoSalida(archivoEntrada);
        final String LETRAS_DNI = "TRWAGMYFPDXBNJZSQVHLCKE";


        try (BufferedReader br = new BufferedReader(new FileReader(archivoEntrada));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoSalida))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                String dniNumerico = String.format("%08d", Integer.parseInt(linea.trim()));


                int numeroDNI = Integer.parseInt(dniNumerico);
                char letra = LETRAS_DNI.charAt(numeroDNI % 23);
                String dniCompleto = dniNumerico + letra;


                bw.write(dniCompleto);
                bw.newLine();
            }
        }
    }


    public File generarNombreArchivoSalida(File archivoEntrada) {
        String nombreOriginal = archivoEntrada.getName();
        String baseNombre = nombreOriginal.contains(".") ? nombreOriginal.substring(0, nombreOriginal.lastIndexOf('.')) : nombreOriginal;
        String extension = nombreOriginal.contains(".") ? nombreOriginal.substring(nombreOriginal.lastIndexOf('.')) : "";

        String nuevoNombre = baseNombre + "_conLetras" + extension;
        return new File(archivoEntrada.getParent(), nuevoNombre);
    }
}