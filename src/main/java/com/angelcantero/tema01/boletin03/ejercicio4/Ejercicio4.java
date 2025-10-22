package com.angelcantero.tema01.boletin03.ejercicio4;

import com.angelcantero.tema01.boletin03.GenericParser;
import com.angelcantero.tema01.boletin03.Pedido;

import java.util.Scanner;

public class Ejercicio4 {
        public static void main(String[] args) {
            Scanner lector=new Scanner(System.in);
            GenericParser parser = new GenericParser();
            Pedido[] pedidos = null;

            try {
                pedidos = parser.parsePedidos();

            } catch (Exception e) {
                System.out.printf(e.getMessage());
            }
            for (Pedido pedido : pedidos) {
                    System.out.println(pedido.getId());


            }
            /*System.out.println("Indica el pedido a buscar:");
            String id = lector.nextLine();
            for (Pedido pedido : pedidos) {
                if (pedido.getId().equals(id)){
                System.out.println(pedido.mostrarItems());

                }
            }*/
            for (Pedido pedido : pedidos) {
                        System.out.println(pedido);
                        System.out.println(calcularTotal(pedido));

            }
        }
        public static Double calcularTotal(Pedido pedido){
            double[] precios = new double[pedido.getItems().length];
            for (int i = 0; i < pedido.getItems().length; i++) {
                precios[i] = pedido.getItems()[i].getPrecioUnitario()*pedido.getItems()[i].getCantidad();
            }
            double total = 0;
            for (double precio : precios) {
                total += precio;
            }
            return total;

        }
}
