package com.angelcantero.tema01.boletin03;

/**
 * Representa la ubicación de un producto dentro del almacén.
 */
public class Ubicacion {
    private int pasillo;
    private String estante;

    /**
     * Constructor por defecto.
     */
    public Ubicacion() {
    }

    /**
     * Constructor con todos los parámetros.
     *
     * @param pasillo El número del pasillo.
     * @param estante La letra o código del estante.
     */
    public Ubicacion(int pasillo, String estante) {
        this.pasillo = pasillo;
        this.estante = estante;
    }

    public int getPasillo() {
        return pasillo;
    }

    public void setPasillo(int pasillo) {
        this.pasillo = pasillo;
    }

    public String getEstante() {
        return estante;
    }

    public void setEstante(String estante) {
        this.estante = estante;
    }

    @Override
    public String toString() {
        return "Ubicacion{" +
                "pasillo=" + pasillo +
                ", estante='" + estante + '\'' +
                '}';
    }
}
