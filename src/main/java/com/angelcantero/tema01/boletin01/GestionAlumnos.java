package com.angelcantero.tema01;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;

public class GestionAlumnos {

    private final File archivoAlumnos;

    /**
     * Constructor que recibe la ruta del archivo. Si no existe, lo crea.
     * @param rutaArchivo La ruta completa al fichero de alumnos.
     * @throws IOException Si ocurre un error al crear el fichero.
     */
    public GestionAlumnos(String rutaArchivo) throws IOException {
        this.archivoAlumnos = new File(rutaArchivo);
        if (!this.archivoAlumnos.exists()) {
            this.archivoAlumnos.createNewFile();
        }
    }

    /**
     * Inserta un array de alumnos en el archivo. Cada alumno en una nueva línea.
     * @param alumnos Array de alumnos a insertar.
     * @throws IOException Si ocurre un error de escritura.
     */
    public void insertarAlumnos(Alumno[] alumnos) throws IOException {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(archivoAlumnos, true))) {
            for (Alumno alumno : alumnos) {
                bw.write(alumno.toCsv());
                bw.newLine();
            }
        }
    }

    /**
     * Elimina un alumno del archivo.
     * @param alumnoAEliminar El objeto Alumno que se desea eliminar.
     * @throws IOException Si ocurre un error de lectura/escritura.
     */
    public void eliminarAlumno(Alumno alumnoAEliminar) throws IOException {
        File archivoTemporal = new File(archivoAlumnos.getAbsolutePath() + ".tmp");
        boolean alumnoEncontrado = false;

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos));
             BufferedWriter bw = new BufferedWriter(new FileWriter(archivoTemporal))) {

            String linea;
            while ((linea = br.readLine()) != null) {
                Alumno alumnoActual = Alumno.fromCsv(linea);
                // Si el alumno actual NO es el que queremos eliminar, lo escribimos en el temporal.
                if (!alumnoActual.equals(alumnoAEliminar)) {
                    bw.write(linea);
                    bw.newLine();
                } else {
                    alumnoEncontrado = true;
                }
            }
        }

        if (alumnoEncontrado) {
            // Reemplazamos el archivo original con el temporal.
            Files.move(archivoTemporal.toPath(), archivoAlumnos.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } else {
            // Si no se encontró el alumno, no es necesario reemplazar, solo borramos el temporal.
            archivoTemporal.delete();
            System.out.println("No se encontró el alumno a eliminar: " + alumnoAEliminar.getNia());
        }
    }

    /**
     * Devuelve una representación en String de todos los alumnos en el archivo.
     * @return Un String con la lista de alumnos.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Lista de Alumnos en el Fichero ---\n");

        try (BufferedReader br = new BufferedReader(new FileReader(archivoAlumnos))) {
            String linea;
            List<Alumno> alumnos = new ArrayList<>();
            while ((linea = br.readLine()) != null) {
                if (!linea.trim().isEmpty()) {
                    alumnos.add(Alumno.fromCsv(linea));
                }
            }

            if (alumnos.isEmpty()) {
                sb.append(" (No hay alumnos registrados)\n");
            } else {
                alumnos.forEach(alumno -> sb.append(alumno.toString()).append("\n"));
            }
        } catch (IOException e) {
            sb.append("Error al leer el archivo de alumnos: ").append(e.getMessage()).append("\n");
        }
        sb.append("--------------------------------------");
        return sb.toString();
    }
}
