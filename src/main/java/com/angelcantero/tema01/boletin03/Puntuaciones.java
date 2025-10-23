package com.angelcantero.tema01.boletin03;

import java.util.Objects;

public class Puntuaciones {
    private double imdb;
    private int rt;

    public Puntuaciones() {
    }

    public Puntuaciones(double imdb, int rt) {
        this.imdb = imdb;
        this.rt = rt;
    }

    public double getImdb() {
        return imdb;
    }

    public void setImdb(double imdb) {
        this.imdb = imdb;
    }

    public int getRt() {
        return rt;
    }

    public void setRt(int rt) {
        this.rt = rt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Puntuaciones that = (Puntuaciones) o;
        return Double.compare(that.imdb, imdb) == 0 && rt == that.rt;
    }

    @Override
    public int hashCode() {
        return Objects.hash(imdb, rt);
    }

    @Override
    public String toString() {
        return "Puntuaciones{" +
               "imdb=" + imdb +
               ", rt=" + rt +
               '}';
    }
}
