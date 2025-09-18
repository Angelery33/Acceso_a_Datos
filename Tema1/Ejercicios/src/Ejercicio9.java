import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

public class Ejercicio9 {

    public boolean compararArchivos(String archivo1, String archivo2) {
        File f1 = new File(archivo1);
        File f2 = new File(archivo2);

        if (!f1.exists() || !f2.exists()) {
            System.out.println("Uno o ambos archivos no existen.");
            return false;
        }

        if (f1.length() != f2.length()) {
            System.out.println("Los archivos tienen distinto tamaño asi que son diferentes.");
            return false;
        }

        try (FileInputStream fis1 = new FileInputStream(f1);
             FileInputStream fis2 = new FileInputStream(f2)) {

            int byte1, byte2;
            while ((byte1 = fis1.read()) != -1) {
                byte2 = fis2.read();
                if (byte1 != byte2) {
                    System.out.println("Los archivos son diferentes.");
                    return false;
                }
            }

            System.out.println("Los archivos son iguales.");
            return true;

        } catch (IOException e) {
            System.out.println("Error al comparar archivos: " + e.getMessage());
            return false;
        }
    }

    public static void main(String[] args) {
        String archivo1 = "C:\\Users\\angel\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos\\Tema1\\Ejercicios\\src" +
                "\\prueba1.txt";
        String archivo = "C:\\Users\\angel\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos\\Tema1\\Ejercicios\\src\\prueba.txt";

        System.out.println("Archivo 1 : " + new File(archivo1).exists());
        System.out.println("Archivo : " + new File(archivo).exists());
        Ejercicio9 x = new Ejercicio9();
        x.compararArchivos(archivo,archivo1);

    }
}

