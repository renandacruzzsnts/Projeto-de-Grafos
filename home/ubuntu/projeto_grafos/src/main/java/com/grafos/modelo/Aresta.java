package com.grafos.modelo;

import java.util.Objects;

public class Aresta {
    private No origem;
    private No destino;
    private int peso;

    public Aresta(No origem, No destino, int peso) {
        this.origem = origem;
        this.destino = destino;
        this.peso = peso;
    }

    public No getOrigem() {
        return origem;
    }

    public No getDestino() {
        return destino;
    }

    public int getPeso() {
        return peso;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Aresta aresta = (Aresta) o;
        return peso == aresta.peso &&
               Objects.equals(origem, aresta.origem) &&
               Objects.equals(destino, aresta.destino);
    }

    @Override
    public int hashCode() {
        return Objects.hash(origem, destino, peso);
    }

    @Override
    public String toString() {
        return "Aresta{" +
               "origem=" + origem.getId() +
               ", destino=" + destino.getId() +
               ", peso=" + peso +
               "}";
    }
}

