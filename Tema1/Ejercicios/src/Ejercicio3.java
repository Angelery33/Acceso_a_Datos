import java.io.*;
public class Ejercicio3 {
    public static void main(String[] args) {
        File carpeta =new File(Config.RUTA);
        System.out.println("Nombre de la carpeta:" + carpeta.getName());
        System.out.println("Ruta de la carpeta:" +carpeta.getAbsolutePath());
        System.out.println("Esta oculta :" +carpeta.isHidden());
        System.out.println("Se puede leer:" +carpeta.canRead());


    }
}
