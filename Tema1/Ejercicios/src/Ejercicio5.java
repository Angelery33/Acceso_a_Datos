public class Ejercicio5 {
    public static void main(String[] args) {
        GestionDeArchivos x=new GestionDeArchivos();
        x.listarDirectorio("C:\\Users\\angel\\OneDrive\\Desktop\\Clase");
        x.crearArchivo(Config.RUTA,"prueba.txt");
        boolean creado = x.crearArchivo("C:\\Users\\angel\\OneDrive\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos" +
                "\\Tema1\\Ejercicios\\src", "prueba.txt");
        System.out.println("Archivo creado: " + creado);
        x.verInfo("C:\\Users\\angel\\OneDrive\\Desktop\\Clase\\AccesoDatos\\Acceso_a_Datos" +
                "\\Tema1\\Ejercicios\\src","prueba.txt");
    }


}
