package com.grafos.algoritmos;

import com.grafos.modelo.Aresta;
import com.grafos.modelo.Grafo;
import com.grafos.modelo.No;
import com.grafos.util.ConjuntoDisjunto;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Kruskal {

    public static List<Aresta> encontrarArvoreGeradoraMinima(Grafo grafo) {
        List<Aresta> resultadoAGM = new ArrayList<>();
        List<Aresta> todasArestas = new ArrayList<>(grafo.getArestas());

        Collections.sort(todasArestas, (a1, a2) -> Integer.compare(a1.getPeso(), a2.getPeso()));

        ConjuntoDisjunto dsu = new ConjuntoDisjunto(new ArrayList<>(grafo.getNos().values()));

        int contadorArestas = 0;
        for (Aresta aresta : todasArestas) {
            if (contadorArestas == grafo.getNumNos() - 1) {
                break;
            }

            No raizOrigem = dsu.encontrar(aresta.getOrigem());
            No raizDestino = dsu.encontrar(aresta.getDestino());

            if (!raizOrigem.equals(raizDestino)) {
                resultadoAGM.add(aresta);
                dsu.unir(raizOrigem, raizDestino);
                contadorArestas++;
            }
        }

        return resultadoAGM;
    }
}

