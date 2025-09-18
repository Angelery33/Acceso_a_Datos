import java.io.File;
public class Ejercicio1 {

        public static void main(String[] args) {

            File carpeta = new File(Config.RUTA);

            if (carpeta.exists()) {
                if (carpeta.isDirectory()) {
                    System.out.println("La carpeta existe y es un directorio.");
                } else {
                    System.out.println("La ruta existe, pero no es un directorio.");
                }
            } else {
                System.out.println("La carpeta no existe.");
            }
        }
    }


