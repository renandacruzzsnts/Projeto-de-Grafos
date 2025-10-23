package com.grafos.algoritmos;

import com.grafos.modelo.Aresta;
import com.grafos.modelo.Grafo;
import com.grafos.modelo.No;
import com.grafos.modelo.DistanciaVertice;

import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class Dijkstra {

    public static Map<No, Integer> encontrarCaminhosMaisCurtos(Grafo grafo, No origem) {
        Map<No, Integer> distancias = new HashMap<>();
        PriorityQueue<DistanciaVertice> filaPrioridade = new PriorityQueue<>();

        for (No no : grafo.getNos().values()) {
            distancias.put(no, Integer.MAX_VALUE);
        }
        distancias.put(origem, 0);
        filaPrioridade.add(new DistanciaVertice(origem, 0));

        while (!filaPrioridade.isEmpty()) {
            DistanciaVertice atual = filaPrioridade.poll();
            No u = atual.getVertice();
            int dist_u = atual.getDistancia();

            if (dist_u > distancias.get(u)) {
                continue;
            }

            for (Aresta aresta : grafo.getListaAdjacencia().get(u)) {
                No v = aresta.getDestino();
                int peso = aresta.getPeso();

                if (distancias.get(u) != Integer.MAX_VALUE && distancias.get(u) + peso < distancias.get(v)) {
                    distancias.put(v, distancias.get(u) + peso);
                    filaPrioridade.add(new DistanciaVertice(v, distancias.get(v)));
                }
            }
        }
        return distancias;
    }
}

