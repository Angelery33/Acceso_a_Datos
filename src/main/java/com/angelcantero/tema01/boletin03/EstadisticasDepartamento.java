package com.angelcantero.tema01.boletin03;

/**
 * Clase auxiliar para guardar la suma total de salarios
 * y el número de empleados de un departamento.
 */
public class EstadisticasDepartamento {

    private int numeroEmpleados = 0;
    private double salarioTotal = 0.0;

    /**
     * Añade el salario de un nuevo empleado a los totales.
     * @param salario El salario del empleado a sumar.
     */
    public void agregarEmpleado(double salario) {
        this.numeroEmpleados++;
        this.salarioTotal += salario;
    }

    public int getNumeroEmpleados() {
        return numeroEmpleados;
    }

    public double getSalarioMedio() {
        if (numeroEmpleados == 0) {
            return 0.0;
        }
        // Calcula la media
        return salarioTotal / numeroEmpleados;
    }
}
