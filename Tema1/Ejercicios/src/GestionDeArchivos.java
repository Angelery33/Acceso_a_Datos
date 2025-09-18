import java.io.File;
import java.io.*;

public class GestionDeArchivos {
    public void leerArchivo(String directorio, String archivo) {
        File f = new File(directorio, archivo);

        try (BufferedReader br = new BufferedReader(new FileReader(f))) {
            String linea;
            System.out.println("Contenido de " + archivo + ":");
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (IOException e) {
            System.out.println("Error al leer el archivo: " + e.getMessage());
        }
    }

    public boolean crearArchivo(String directorio, String archivo){
        try {
            File file = new File(directorio, archivo);
            return file.createNewFile();
        }
        catch (IOException e){
            return false;
        }

    }

    public void listarDirectorio(String directorio){
        File file = new File(directorio);
        if (file.exists() && file.isDirectory()){
            File[] archivos = file.listFiles();
            if(archivos != null){
                for (File f : archivos){
                    System.out.println("-------");
                    System.out.println("Archivo");
                    System.out.println("-------");
                    System.out.println(f.getName());
                    if (f.isDirectory()){System.out.println("d");}
                    else {System.out.println("f");}
                    System.out.println(f.length()+ "bytes");
                    String permisos=(f.canRead()? "r": "-")+(f.canWrite()? "w": "-")+(f.canExecute()? "x": "-");
                    System.out.println(permisos);
                    System.out.println("-------");

                }
            }
        }
    }
    public void verInfo(String directorio, String archivo) {
        File f = new File(directorio, archivo);

        if (f.exists()) {
            System.out.println("Nombre: " + f.getName());
            System.out.println("Ruta absoluta: " + f.getAbsolutePath());
            System.out.println("Se puede escribir: " + f.canWrite());
            System.out.println("Se puede leer: " + f.canRead());
            System.out.println("Tamaño en bytes: " + f.length());
            String tipo= f.isDirectory()? "Carpeta": "Fichero";
            System.out.println("Tipo de archivo: " + tipo);

        } else {
            System.out.println("El archivo o directorio no existe.");
        }
    }
}
