package com.grafos.modelo;

public class DistanciaVertice implements Comparable<DistanciaVertice> {
    private No vertice;
    private int distancia;

    public DistanciaVertice(No vertice, int distancia) {
        this.vertice = vertice;
        this.distancia = distancia;
    }

    public No getVertice() {
        return vertice;
    }

    public int getDistancia() {
        return distancia;
    }

    @Override
    public int compareTo(DistanciaVertice other) {
        return Integer.compare(this.distancia, other.distancia);
    }
}

