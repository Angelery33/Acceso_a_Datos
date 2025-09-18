import java.io.*;

public class Ejercicio8 {

    public static void main(String[] args) {

        String rutaArchivo = args[0];
        File archivo = new File(rutaArchivo);

        if (!archivo.exists()) {
            System.out.println("El archivo no existe: " + rutaArchivo);
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            System.out.println("Contenido de " + rutaArchivo + ":");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }
}
