import java.io.*;


public class Ejercicio2 {
    public static void main(String[] args) {


            File carpeta = new File(Config.RUTA);

            if (carpeta.exists() && carpeta.isDirectory()) {
                File[] contenido = carpeta.listFiles();

                if (contenido != null && contenido.length > 0) {
                    System.out.println("Contenido de la carpeta:");
                    for (File f : contenido) {
                        if (f.isDirectory()) {
                            System.out.println("Carpeta :" + f.getName());
                        } else {
                            System.out.println("Archivo :" + f.getName());
                        }
                    }
                } else {
                    System.out.println("La carpeta está vacía.");
                }
            } else {
                System.out.println("La ruta no existe o no es un directorio.");
            }
        }
}
