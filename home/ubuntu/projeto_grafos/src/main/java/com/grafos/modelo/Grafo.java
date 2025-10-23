package com.grafos.modelo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Grafo {
    private Map<Integer, No> nos;
    private List<Aresta> arestas;
    private Map<No, List<Aresta>> listaAdjacencia;
    private int numNos;
    private int numArestas;

    public Grafo(int numNos, int numArestas) {
        this.numNos = numNos;
        this.numArestas = numArestas;
        this.nos = new HashMap<>();
        this.arestas = new ArrayList<>();
        this.listaAdjacencia = new HashMap<>();
        for (int i = 1; i <= numNos; i++) {
            No no = new No(i);
            nos.put(i, no);
            listaAdjacencia.put(no, new ArrayList<>());
        }
    }

    public void adicionarAresta(int idOrigem, int idDestino, int peso) {
        No origem = nos.get(idOrigem);
        No destino = nos.get(idDestino);
        if (origem != null && destino != null) {
            Aresta aresta = new Aresta(origem, destino, peso);
            arestas.add(aresta);
            listaAdjacencia.get(origem).add(aresta);
            // Para grafos não direcionados, adicione a aresta reversa também
            listaAdjacencia.get(destino).add(new Aresta(destino, origem, peso));
        } else {
            System.err.println("Erro: Nó não encontrado para aresta: " + idOrigem + "-" + idDestino);
        }
    }

    public Map<Integer, No> getNos() {
        return nos;
    }

    public List<Aresta> getArestas() {
        return arestas;
    }

    public Map<No, List<Aresta>> getListaAdjacencia() {
        return listaAdjacencia;
    }

    public int getNumNos() {
        return numNos;
    }

    public int getNumArestas() {
        return numArestas;
    }
}

