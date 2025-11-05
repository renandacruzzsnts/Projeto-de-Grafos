package com.grafos.algoritmos;

import com.grafos.modelo.Aresta;
import com.grafos.modelo.Grafo;
import com.grafos.modelo.No;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

public class Prim {

    public static List<Aresta> encontrarArvoreGeradoraMinima(Grafo grafo) {
        List<Aresta> resultadoAGM = new ArrayList<>();
        Set<No> visitados = new HashSet<>();
        PriorityQueue<Aresta> filaPrioridade = new PriorityQueue<>(Comparator.comparingInt(Aresta::getPeso));

        if (grafo.getNos().isEmpty()) {
            return resultadoAGM;
        }

        No noInicial = grafo.getNos().get(1); 
        if (noInicial == null) {
            // Se o nó 1 não existe, pega o primeiro disponível
            noInicial = grafo.getNos().values().iterator().next();
        }
        
        visitados.add(noInicial);

        for (Aresta aresta : grafo.getListaAdjacencia().get(noInicial)) {
            filaPrioridade.add(aresta);
        }

        while (!filaPrioridade.isEmpty() && resultadoAGM.size() < grafo.getNumNos() - 1) {
            Aresta arestaAtual = filaPrioridade.poll();
            No u = arestaAtual.getOrigem();
            No v = arestaAtual.getDestino();

            if (visitados.contains(v)) {
                continue;
            }

            resultadoAGM.add(arestaAtual);
            visitados.add(v);

            for (Aresta aresta : grafo.getListaAdjacencia().get(v)) {
                if (!visitados.contains(aresta.getDestino())) {
                    filaPrioridade.add(aresta);
                }
            }
        }
        return resultadoAGM;
    }
}

