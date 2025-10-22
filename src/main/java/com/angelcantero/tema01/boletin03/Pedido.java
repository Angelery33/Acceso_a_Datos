package com.angelcantero.tema01.boletin03;

import java.util.Arrays;
import java.util.Objects;

public class Pedido {
    private String id;
    private Cliente cliente; // Objeto Cliente anidado
    private String fecha;
    private ItemPedido[] items; // Array de Items anidado
    private double total;
    private String monedaTotal; // Moneda del total

    public Pedido(String id, Cliente cliente, String fecha, ItemPedido[] items, double total, String monedaTotal) {
        this.id = id;
        this.cliente = cliente;
        this.fecha = fecha;
        this.items = items;
        this.total = total;
        this.monedaTotal = monedaTotal;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public ItemPedido[] getItems() {
        return items;
    }

    public void setItems(ItemPedido[] items) {
        this.items = items;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public String getMonedaTotal() {
        return monedaTotal;
    }

    public void setMonedaTotal(String monedaTotal) {
        this.monedaTotal = monedaTotal;
    }
    public String mostrarItems() {
        StringBuilder sb = new StringBuilder();
        for (ItemPedido item : items) {
            sb.append(item.getDescripcion()).append("\n");
            sb.append("Cantidad: ").append(item.getCantidad()).append("\n");
            sb.append("Precio Unitario: ").append(item.getPrecioUnitario()).append(" ").append(item.getMoneda()).append("\n");
        }
        return sb.toString();
        }

    @Override
    public String toString() {
        return "Pedido{" +
                "id='" + id + '\'' +
                ", cliente=" + cliente +
                ", fecha='" + fecha + '\'' +
                ", items=" + Arrays.toString(items) +
                ", total=" + total +
                ", monedaTotal='" + monedaTotal + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pedido pedido = (Pedido) o;
        // El 'id' del pedido es el identificador único
        return Objects.equals(id, pedido.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
