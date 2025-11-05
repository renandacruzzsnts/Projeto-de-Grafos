package com.grafos.aplicacao;

import com.grafos.algoritmos.Dijkstra;
import com.grafos.algoritmos.Kruskal;
import com.grafos.algoritmos.Prim;
import com.grafos.modelo.Aresta;
import com.grafos.modelo.Grafo;
import com.grafos.modelo.No;
import com.grafos.util.LeitorGrafo;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public class Main {

    public static void main(String[] args) {
        String[] arquivosGrafo = {
            "projeto_grafos/dados/USA-road-d.NY.gr",
            "projeto_grafos/dados/USA-road-d.BAY.gr",
            "projeto_grafos/dados/USA-road-d.COL.gr"
        };

        System.out.println("\n--- Resultados dos Algoritmos ---");
        System.out.println("| Grafo | Vértices | Arestas | Dijkstra (Custo) | Dijkstra (Tempo s) | Kruskal (Custo) | Kruskal (Tempo s) | Prim (Custo) | Prim (Tempo s) |");
        System.out.println("|-------|----------|---------|------------------|--------------------|-----------------|-------------------|--------------|----------------|");

        for (String caminhoArquivo : arquivosGrafo) {
            try {
                Grafo grafo = LeitorGrafo.lerGrafo(caminhoArquivo);
                String nomeGrafo = caminhoArquivo.substring(caminhoArquivo.lastIndexOf("/") + 1).replace(".gr", "");

                long inicioTempoDijkstra = System.nanoTime();
                No noOrigem = grafo.getNos().get(1);
                Map<No, Integer> resultadoDijkstra = Dijkstra.encontrarCaminhosMaisCurtos(grafo, noOrigem);
                long fimTempoDijkstra = System.nanoTime();
                double duracaoDijkstra = (fimTempoDijkstra - inicioTempoDijkstra) / 1_000_000_000.0;
                long custoTotalDijkstra = resultadoDijkstra.values().stream().filter(d -> d != Integer.MAX_VALUE).mapToLong(Integer::longValue).sum();

                long inicioTempoKruskal = System.nanoTime();
                List<Aresta> resultadoKruskal = Kruskal.encontrarArvoreGeradoraMinima(grafo);
                long fimTempoKruskal = System.nanoTime();
                double duracaoKruskal = (fimTempoKruskal - inicioTempoKruskal) / 1_000_000_000.0;
                long custoTotalKruskal = resultadoKruskal.stream().mapToLong(Aresta::getPeso).sum();

                long inicioTempoPrim = System.nanoTime();
                List<Aresta> resultadoPrim = Prim.encontrarArvoreGeradoraMinima(grafo);
                long fimTempoPrim = System.nanoTime();
                double duracaoPrim = (fimTempoPrim - inicioTempoPrim) / 1_000_000_000.0;
                long custoTotalPrim = resultadoPrim.stream().mapToLong(Aresta::getPeso).sum();

                System.out.printf("| %s | %d | %d | %d | %.4f | %d | %.4f | %d | %.4f |%n",
                        nomeGrafo, grafo.getNumNos(), grafo.getArestas().size(),
                        custoTotalDijkstra, duracaoDijkstra,
                        custoTotalKruskal, duracaoKruskal,
                        custoTotalPrim, duracaoPrim);

            } catch (IOException e) {
                System.err.println("Erro ao ler o arquivo " + caminhoArquivo + ": " + e.getMessage());
            } catch (Exception e) {
                System.err.println("Erro ao processar o grafo " + caminhoArquivo + ": " + e.getMessage());
                e.printStackTrace();
            }
        }
    }
}

