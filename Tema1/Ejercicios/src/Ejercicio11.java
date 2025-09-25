import java.io.*;

public class Ejercicio11 {
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

            String linea1,linea2;

            while (true) {
                linea1 = br1.readLine();
                linea2 = br2.readLine();

                if (linea1 == null && linea2 == null) {
                    break;
                }
                if (linea1 != null) {
                    bw.write(linea1);

                }
                if (linea2 != null) {
                    bw.write(linea2);
                    bw.newLine();
                }
            }

            System.out.println("Archivo concatenado creado: " + archivoFinal);

        } catch (IOException e) {
            System.out.println("Error al concatenar archivos: " + e.getMessage());
        }
    }

    public static void main(String[] args) {
        Ejercicio11 x = new Ejercicio11();
        x.concat("C:\\Users\\angel\\Desktop\\Clase\\Acceso_a_Datos\\Tema1\\Ejercicios\\src\\prueba.txt", "C" +
                ":\\Users\\angel\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos\\Tema1\\Ejercicios\\src\\prueba1.txt", "archivoFinal1.txt");

    }
}
