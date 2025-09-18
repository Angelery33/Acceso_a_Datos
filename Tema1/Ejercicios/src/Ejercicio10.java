import java.io.*;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;


public class Ejercicio10 {
    public void concat(String archivo1, String archivo2, String archivoFinal) {
        File f1 = new File(archivo1);
        File f2 = new File(archivo2);
        File f3 = new File(archivoFinal);

        if (!f1.exists() || !f2.exists()) {
            System.out.println("Uno o ambos archivos de entrada no existen.");
            return;
        }

        try (BufferedReader br1 = new BufferedReader(new FileReader(f1));
             BufferedReader br2 = new BufferedReader(new FileReader(f2));
             BufferedWriter bw = new BufferedWriter(new FileWriter(f3))) {

            String linea;

            while ((linea = br1.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }
            while ((linea = br2.readLine()) != null) {
                bw.write(linea);
                bw.newLine();
            }

            System.out.println("Archivo concatenado creado: " + archivoFinal);

        } catch (IOException e) {
            System.out.println("Error al concatenar archivos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Ejercicio10 x = new Ejercicio10();
        x.concat("C:\\Users\\angel\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos\\Tema1\\Ejercicios\\src\\prueba.txt", "C" +
                ":\\Users\\angel\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos\\Tema1\\Ejercicios\\src" +
                "\\prueba1.txt", "archivoFinal.txt");

    }
}
