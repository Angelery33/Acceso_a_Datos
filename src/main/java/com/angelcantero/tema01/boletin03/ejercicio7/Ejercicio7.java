package com.angelcantero.tema01.boletin03.ejercicio7;

import com.angelcantero.tema01.boletin03.*;

import java.util.Scanner;

public class Ejercicio7 {
    public static void main(String[] args) {
        Scanner lector=new Scanner(System.in);
        GenericParser parser = new GenericParser();
        Inventario inventario = null;
        try{
            inventario = parser.parseInventario();

        }
        catch (Exception e){
            System.out.printf(e.getMessage());
        }
        Producto[] productosInventario = inventario.getProductos();
        for (Producto producto : productosInventario) {
            System.out.println("Producto : "+producto.getNombre());

            System.out.println("Id del producto : "+producto.getId());
            }
                System.out.println("Elige el id del producto para saber u ubicación");
                String id = lector.nextLine();
                for (Producto producto : productosInventario) {
                    if (producto.getId().equals(id)){
                                System.out.println("Ubicación del producto seleccionado : "+producto.getUbicacion());

                    }
                }




    }
}
