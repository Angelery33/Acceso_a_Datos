package com.angelcantero.tema01.boletin03.ejercicio5;

import com.angelcantero.tema01.boletin03.Alumno;
import com.angelcantero.tema01.boletin03.GenericParser;


public class Ejercicio5 {
    public static void main(String[] args) {
        GenericParser parser = new GenericParser();
        Alumno[] alumnos = null;
        try{
            alumnos = parser.parseAlumnos();

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }
        for (Alumno alumno : alumnos) {
            System.out.println("Alumno:");
            System.out.println("Nombre del alumno :"+alumno.getNombre()+"\n"+"Fecha de nacimiento : "+alumno.getFechaNacimiento()+"\n");
        }
    }

}
