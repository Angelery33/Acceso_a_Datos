package com.angelcantero.tema01.boletin03;

/**
 * Proporciona métodos estáticos para realizar cálculos estadísticos básicos sobre un conjunto de datos numéricos.
 * Esta clase de utilidad ofrece funcionalidades para encontrar el valor máximo, el valor mínimo y el promedio
 * de un array de números de tipo double.
 * <p>
 * <b>Nota importante:</b> Los métodos de esta clase no manejan arrays vacíos o nulos.
 * Si se proporciona un array vacío, se lanzará una {@link ArrayIndexOutOfBoundsException}.
 * Se recomienda validar los datos de entrada antes de invocar estos métodos.
 */
public class EstadisiticasGenerales {
    /**
     * Encuentra y devuelve el valor máximo en un array de números de tipo double.
     * El método itera a través del array para identificar el número más grande.
     *
     * @param numbers El array de números de tipo double. No debe ser nulo ni vacío.
     * @return El número más grande (valor máximo) encontrado en el array.
     * @throws ArrayIndexOutOfBoundsException si el array proporcionado está vacío.
     */
    public static double maxNumber(double[] numbers){
        double max = numbers[0];
        for (double number : numbers) {
            if (number > max) {
                max = number;
            }
        }
        return max;

    }

    /**
     * Encuentra y devuelve el valor mínimo en un array de números de tipo double.
     * El método recorre el array para identificar el número más pequeño.
     *
     * @param numbers El array de números de tipo double. No debe ser nulo ni vacío.
     * @return El número más pequeño (valor mínimo) encontrado en el array.
     * @throws ArrayIndexOutOfBoundsException si el array proporcionado está vacío.
     */
    public static double minNumber(double[] numbers){
        double min = numbers[0];
        for (double number : numbers) {
            if (number < min) {
                min = number;
            }
        }
        return min;

    }

    /**
     * Calcula y devuelve el valor promedio de los elementos en un array de doubles.
     * El promedio se calcula sumando todos los números y dividiendo por la cantidad de elementos.
     *
     * @param numbers El array de números de tipo double. No debe ser nulo ni vacío.
     * @return El valor promedio de los números en el array.
     * @throws ArithmeticException si el array está vacío, ya que implicaría una división por cero.
     */
    public static double average(double[] numbers){
        double sum = 0;
        for (double number : numbers) {
            sum += number;
        }
        return sum / numbers.length;

    }


}
