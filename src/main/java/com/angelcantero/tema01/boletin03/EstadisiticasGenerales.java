package com.angelcantero.tema01.boletin03;

public class EstadisiticasGenerales {
    public static double maxNumber(double[] numbers){
        double max = numbers[0];
        for (double number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;

    }
    public static double minNumber(double[] numbers){
        double min = numbers[0];
        for (double number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;

    }
    public static double average(double[] numbers){
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;

    }


}
