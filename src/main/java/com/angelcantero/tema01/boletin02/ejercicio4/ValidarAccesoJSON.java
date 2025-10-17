package com.angelcantero.tema01.boletin02.ejercicio4;



import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.*;

/**
 * Clase para gestionar el acceso de múltiples usuarios usando un archivo JSON.
 */
public class ValidarAccesoJSON {

    private static final String FILE_NAME = "usuarios.json";
    private static final Gson gson = new GsonBuilder().setPrettyPrinting().create();

    /**
     * Convierte una cadena de texto en un codigo hash bajo el algoritmo SHA-256.
     * @param input
     * @return
     * @throws NoSuchAlgorithmException
     */
    public String getHashSHA256(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    /**
     * Valida el acceso de un usuario comprobando su contraseña.
     *
     * @param usuario nombre del usuario
     * @param password contraseña en texto plano
     * @return true si el hash coincide, false en caso contrario
     */
    public boolean validarAcceso(String usuario, String password) throws Exception {
        Map<String, String> usuarios = cargarUsuarios();
        if (!usuarios.containsKey(usuario)) {
            return false;
        }
        String hashGuardado = usuarios.get(usuario);
        String hashIntroducido = getHashSHA256(password);
        return hashGuardado.equals(hashIntroducido);
    }

    /**
     * Carga todos los usuarios desde el archivo JSON.
     * Si el archivo no existe, se crea con un usuario por defecto.
     * @return mapa de usuarios (usuario → hash)
     * @throws Exception si ocurre un error al leer o crear el archivo
     *
     */
    public Map<String, String> cargarUsuarios() throws Exception          {
        File file = new File(FILE_NAME);
        if (!file.exists()) {
            Map<String, String> usuarios = new HashMap<>();
            usuarios.put("admin", getHashSHA256("S3cret@"));
            guardarUsuarios(usuarios);
            return usuarios;

        }

        try (Reader reader = new FileReader(file)) {
            Type type = new TypeToken<Map<String, String>>() {}.getType();
            Map<String, String> usuarios = gson.fromJson(reader, type);
            return usuarios != null ? usuarios : new HashMap<>();
        }
    }

    /**
     * Guarda el mapa completo de usuarios (usuario → hash) en el archivo JSON.
     * @param usuarios mapa de usuarios
     * @throws IOException si ocurre un error al escribir en el archivo
     *
     */
    public void guardarUsuarios(Map<String, String> usuarios) throws IOException {
        try (Writer writer = new FileWriter(FILE_NAME)) {
            gson.toJson(usuarios, writer);
        }
    }

    /**
     * Agrega un nuevo usuario al archivo.
     * @param usuario
     * @param password
     * @throws Exception
     */
    public void agregarUsuario(String usuario, String password) throws Exception {
        Map<String, String> usuarios = cargarUsuarios();
        usuarios.put(usuario, getHashSHA256(password));
        guardarUsuarios(usuarios);
    }

    /**
     * Elimina un usuario existente.
     * @param usuario
     * @throws Exception
     *
     */
    public void eliminarUsuario(String usuario) throws Exception {
        Map<String, String> usuarios = cargarUsuarios();
        if (usuarios.remove(usuario) != null) {
            guardarUsuarios(usuarios);
        }
    }
    
    public static void main(String[] args) {
        ValidarAccesoJSON validador = new ValidarAccesoJSON();
        try {
            System.out.println("Agregando usuario 'testuser' con contraseña 'testpass'...");
            validador.agregarUsuario("testuser", "testpass");
            System.out.println("Usuario agregado.");

            System.out.println("\nValidando acceso para 'testuser' con contraseña correcta...");
            if (validador.validarAcceso("testuser", "testpass")) {
                System.out.println("Acceso concedido.");
            } else {
                System.out.println("Acceso denegado.");
            }

            System.out.println("\nValidando acceso para 'testuser' con contraseña incorrecta...");
            if (validador.validarAcceso("testuser", "wrongpass")) {
                System.out.println("Acceso concedido.");
            } else {
                System.out.println("Acceso denegado.");
            }

            System.out.println("\nEliminando usuario 'testuser'...");
            validador.eliminarUsuario("testuser");
            System.out.println("Usuario eliminado.");

            System.out.println("\nIntentando validar acceso para usuario eliminado...");
             if (validador.validarAcceso("testuser", "testpass")) {
                System.out.println("Acceso concedido. (Esto no debería pasar)");
            } else {
                System.out.println("Acceso denegado como se esperaba.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}