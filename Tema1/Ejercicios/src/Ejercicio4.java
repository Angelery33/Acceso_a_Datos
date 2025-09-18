import java.io.File;
import java.time.format.DateTimeFormatter;

public class Ejercicio4 {
    public static void main(String[] args) {
        File carpeta = new File(Config.RUTA);
        File[] contenido = carpeta.listFiles();
        if (contenido != null) {
            File archivo1 = contenido[1];
            System.out.println("Nombre del archivo: " + archivo1.getName());
            System.out.println("Ruta del archivo: " + archivo1.getAbsolutePath());
            System.out.println("Esta oculta :" + archivo1.isHidden());
            System.out.println("Se puede leer :" + archivo1.canRead());
            System.out.println("Se puede escribir : "+ archivo1.canWrite());
            DateTimeFormatter x= DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
            System.out.println("Ultima modificacion : "+ archivo1.lastModified());
            System.out.println("Tamaño en bytes,KB y MB  : \n"+ archivo1.length()+" Bytes\n"+(archivo1.length()/1024+
                    " KB")+"\n"+((double)archivo1.length()/1024/1024+" MB"));

        } else {
            System.out.println("El archivo no existe");


        }
    }
}
