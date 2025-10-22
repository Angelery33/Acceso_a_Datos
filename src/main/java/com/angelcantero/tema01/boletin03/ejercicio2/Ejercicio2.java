package com.angelcantero.tema01.boletin03.ejercicio2;

import com.angelcantero.tema01.boletin03.Empleado;
import com.angelcantero.tema01.boletin03.GenericParser;
import com.angelcantero.tema01.boletin03.EstadisticasDepartamento;

import java.util.HashMap;
import java.util.Map;


public class Ejercicio2 {
    public static void main(String[] args) {
        GenericParser parser = new GenericParser();
        Empleado[] empleados = null;
        try{
            empleados = parser.parseEmpleados();

        }
        catch (Exception e){
            System.out.println("Error al parsear el XML: " + e.getMessage());
            e.printStackTrace();
            return;
        }
        for (Empleado empleado : empleados) {

            System.out.println(empleado.stEjercicio1());
        }
        System.out.println("\n--- Estadísticas por Departamento ---");
        calcularYMostrarEstadisticas(empleados);


    }
    /**
     * Este método completa la lógica que empezaste.
     * Recibe el array de empleados, calcula las estadísticas
     * y las muestra por pantalla.
     *
     * @param empleados El array de empleados parseado del XML.
     */
    public static void calcularYMostrarEstadisticas(Empleado[] empleados) {

        HashMap<String, EstadisticasDepartamento> statsPorDepto = new HashMap<>();


        for (Empleado emp : empleados) {
            String depto = emp.getDepartamento();
            double salario = emp.getSalario();


            EstadisticasDepartamento stats = statsPorDepto.computeIfAbsent(depto, k -> new EstadisticasDepartamento());

            stats.agregarEmpleado(salario);
        }

        System.out.println("Departamento         | Nº empleados | Salario medio");
        System.out.println("-----------------------------------------------------");

        for (Map.Entry<String, EstadisticasDepartamento> entry : statsPorDepto.entrySet()) {
            String depto = entry.getKey();
            EstadisticasDepartamento stats = entry.getValue();

            System.out.printf("%-20s | %-12d | %,.2f$%n",
                    depto,
                    stats.getNumeroEmpleados(),
                    stats.getSalarioMedio());
        }
    }
}

