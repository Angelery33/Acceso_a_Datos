package com.angelcantero.tema01.boletin03;

import java.util.Objects;

public class Empleado {
    private String id;
    private String nombre;
    private String departamento;
    private double salario;
    private String moneda;
    private String fechaAlta;

    // Constructor, getters y setters...
    public Empleado(String id, String nombre, String departamento,
                    double salario, String moneda, String fechaAlta) {
        this.id = id;
        this.nombre = nombre;
        this.departamento = departamento;
        this.salario = salario;
        this.moneda = moneda;
        this.fechaAlta = fechaAlta;
    }

    public String getId() {
        return id;
    }


    public String getNombre() {
        return nombre;
    }


    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }



    public String stEjercicio1() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", salario=" + salario +
                ", moneda='" + moneda + '\'' +
                '}';
    }

    @Override
    public String toString() {
        return "Empleado{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", departamento='" + departamento + '\'' +
                ", salario=" + salario +
                ", moneda='" + moneda + '\'' +
                ", fechaAlta='" + fechaAlta + '\'' +
                '}';
    }


    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Empleado empleado = (Empleado) o;
        return Objects.equals(id, empleado.id);
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }
}