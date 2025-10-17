package com.angelcantero.tema01.boletin02;

import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.Properties;

public class ValidarAcceso {

    /**
     * Genera el hash SHA-1 correspondiente a un texto.
     *
     * @param input texto a convertir en hash
     * @return cadena hexadecimal representando el hash SHA-256
     * @throws NoSuchAlgorithmException si no se encuentra el algoritmo SHA-256
     */
    public String getHashSHA1(String input) throws NoSuchAlgorithmException {
        MessageDigest md = MessageDigest.getInstance("SHA-256");
        byte[] messageDigest = md.digest(input.getBytes());
        StringBuilder hexString = new StringBuilder();
        for (byte b : messageDigest) {
            hexString.append(String.format("%02X", 0xFF & b));
        }
        return hexString.toString();
    }

    /**
     * Valida una contraseña comparando su hash con el hash almacenado.
     *
     * @param psw contraseña introducida por el usuario
     * @param hashGuardado hash almacenado previamente en el archivo properties
     * @return  true si el hash coincide, false en caso contrario
     */
    public boolean validarAcceso(String psw, String hashGuardado) throws NoSuchAlgorithmException {
        String hashIntroducido = getHashSHA1(psw);
        return hashIntroducido.equals(hashGuardado);
    }

    /**
     * Carga el hash de la contraseña desde el archivo config.properties.
     * Si el archivo no existe, se crea con una contraseña por defecto ("S3cret@").
     *
     * @return hash de la contraseña guardada
     * @throws RuntimeException si ocurre un error al leer o crear el archivo
     */
    public String cargarHash() {
        try {
            File file = new File("config.properties");
            Properties prop = new Properties();
            if (file.exists()) {
                try (FileInputStream fis = new FileInputStream(file)) {
                    prop.load(fis);
                    return prop.getProperty("password");
                }
            } else {
                String hashDefecto = getHashSHA1("S3cret@");
                guardarHash(hashDefecto);
                return hashDefecto;
            }
        } catch (IOException | NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Guarda el hash de la contraseña en el archivo config.properties}.
     *
     * @param hash cadena hexadecimal representando el hash SHA-256 de la contraseña
     * @throws IOException si ocurre un error al escribir en el archivo
     */
    public void guardarHash(String hash) throws IOException {
        Properties props = new Properties();
        props.setProperty("password", hash);
        try (FileOutputStream fos = new FileOutputStream("config.properties")) {
            props.store(fos, "Archivo de configuración de acceso");
        }
    }
}