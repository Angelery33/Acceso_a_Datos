package com.angelcantero.tema02.boletin01;

import com.angelcantero.tema02.Menu;
import com.angelcantero.tema02.conexionbd.ConexionBD;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;


public class Ejercicio1 {
    private static final Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        ConexionBD con = new ConexionBD();
        Menu m = new Menu();
        boolean parar = true;
        while (parar) {
            Menu.mostrarMenu();

        }


    }

}
