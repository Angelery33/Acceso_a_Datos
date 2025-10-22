package com.angelcantero.tema01.boletin03.ejercicio1;


import com.angelcantero.tema01.boletin03.Empleado;
import com.angelcantero.tema01.boletin03.GenericParser;


public class Ejercicio1 {


    public static void main(String[] args) {
        GenericParser parser = new GenericParser();
        Empleado[] empleados = null;
        try{
             empleados = parser.parseEmpleados();

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }
        for (Empleado empleado : empleados) {

        System.out.println(empleado.stEjercicio1());
        }

    }
}
