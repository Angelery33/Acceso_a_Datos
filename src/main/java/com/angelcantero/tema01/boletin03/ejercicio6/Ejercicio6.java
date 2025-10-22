package com.angelcantero.tema01.boletin03.ejercicio6;

import com.angelcantero.tema01.boletin03.Alumno;
import com.angelcantero.tema01.boletin03.EstadisiticasGenerales;
import com.angelcantero.tema01.boletin03.GenericParser;

public class Ejercicio6 {
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
            double[] notas = new double[alumno.getNotas().length];
            for (int i = 0; i < alumno.getNotas().length; i++) {
                notas[i] = alumno.getNotas()[i].getNota();
            }

            System.out.println("Alumno:");
            System.out.println("Nombre del alumno :"+alumno.getNombre());
            System.out.println("Nota mas alta : "+ EstadisiticasGenerales.maxNumber(notas));
        }

        for (Alumno alumno : alumnos) {
            double[] notas = new double[alumno.getNotas().length];
            for (int i = 0; i < alumno.getNotas().length; i++) {
                notas[i] = alumno.getNotas()[i].getNota();
            }
            double notaMedia= EstadisiticasGenerales.average(notas);
            alumno.setNotaMedia(notaMedia);


        }
        Alumno alMaxAverage=alumnos[0];
        for (Alumno alumno : alumnos) {
            double notaMedia=alumno.getNotaMedia();
            if (notaMedia>alMaxAverage.getNotaMedia())
                alMaxAverage=alumno;



        }
        System.out.println();
        System.out.println("Alumno:");
        System.out.println("Nombre del alumno con la nota media mas alta :"+alMaxAverage.getNombre());
        System.out.println("Nota media : "+ alMaxAverage.getNotaMedia());
    }
}
