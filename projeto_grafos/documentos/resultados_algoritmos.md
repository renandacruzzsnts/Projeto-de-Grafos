# Estudo e Implementação de Algoritmos de Caminho Mínimo e Árvore Geradora Mínima

## Pseudocódigos dos Algoritmos Implementados

A seguir, são apresentados os pseudocódigos dos algoritmos de Dijkstra, Kruskal e Prim, com destaque para os aspectos relevantes de suas implementações em Java.

### Algoritmo de Dijkstra (Caminho Mínimo)

O algoritmo de Dijkstra encontra o caminho mais curto de um nó de origem para todos os outros nós em um grafo com pesos de aresta não negativos. A implementação utiliza uma fila de prioridade para selecionar o próximo nó a ser visitado, garantindo que o nó com a menor distância conhecida seja processado primeiro.

```
Função Dijkstra(Grafo, Nó_Origem):
  Para cada Nó V no Grafo:
    Distância[V] = Infinito
    Anterior[V] = Indefinido
  Distância[Nó_Origem] = 0
  Fila_Prioridade = { (0, Nó_Origem) } // (distância, nó)

  Enquanto Fila_Prioridade não está vazia:
    (dist, U) = Extrair_Mínimo(Fila_Prioridade)

    Se dist > Distância[U]:
      Continuar // Já encontrou um caminho mais curto para U

    Para cada Aresta (U, V, Peso) adjacente a U:
      Se Distância[U] + Peso < Distância[V]:
        Distância[V] = Distância[U] + Peso
        Anterior[V] = U
        Adicionar_ou_Atualizar(Fila_Prioridade, (Distância[V], V))

  Retornar Distância, Anterior
```

### Algoritmo de Kruskal (Árvore Geradora Mínima)

O algoritmo de Kruskal encontra uma Árvore Geradora Mínima (AGM) para um grafo não direcionado e ponderado. Ele funciona adicionando arestas em ordem crescente de peso, desde que não formem um ciclo com as arestas já adicionadas. A implementação utiliza a estrutura de dados Union-Find (Disjoint Set Union - DSU) para detectar ciclos eficientemente.

```
Função Kruskal(Grafo):
  AGM = Lista_Vazia
  Todas_Arestas = Obter_Todas_Arestas(Grafo)
  Ordenar(Todas_Arestas por Peso_Crescente)
  DSU = Nova_Estrutura_DSU(Todos_Nós_do_Grafo)

  Para cada Aresta (U, V, Peso) em Todas_Arestas:
    Se DSU.Find(U) != DSU.Find(V): // Se U e V não estão no mesmo componente (não forma ciclo)
      Adicionar Aresta à AGM
      DSU.Union(U, V)
    Se número de arestas na AGM == Número_de_Nós - 1:
      Parar // AGM completa

  Retornar AGM
```

### Algoritmo de Prim (Árvore Geradora Mínima)

O algoritmo de Prim também encontra uma Árvore Geradora Mínima (AGM). Ele cresce a AGM a partir de um nó inicial, adicionando a cada passo a aresta de menor peso que conecta um nó na AGM a um nó fora dela. A implementação utiliza uma fila de prioridade para selecionar a próxima aresta a ser adicionada.

```
Função Prim(Grafo):
  AGM = Lista_Vazia
  Nós_Visitados = Conjunto_Vazio
  Fila_Prioridade = Nova_Fila_Prioridade // Armazena arestas (Peso, U, V)

  Escolher_Nó_Inicial S
  Adicionar S a Nós_Visitados

  Para cada Aresta (S, V, Peso) adjacente a S:
    Adicionar (Peso, S, V) à Fila_Prioridade

  Enquanto Fila_Prioridade não está vazia e tamanho da AGM < Número_de_Nós - 1:
    (Peso, U, V) = Extrair_Mínimo(Fila_Prioridade)

    Se V não está em Nós_Visitados:
      Adicionar Aresta (U, V, Peso) à AGM
      Adicionar V a Nós_Visitados

      Para cada Aresta (V, W, Peso_VW) adjacente a V:
        Se W não está em Nós_Visitados:
          Adicionar (Peso_VW, V, W) à Fila_Prioridade

  Retornar AGM
```

## Resultados Computacionais

A tabela a seguir apresenta os resultados da execução dos algoritmos de Dijkstra, Kruskal e Prim nas instâncias de grafos fornecidas (New York, San Francisco Bay Area e Colorado). Os valores de custo para Dijkstra representam a soma das distâncias dos caminhos mínimos a partir do nó 1 para todos os outros nós alcançáveis. Para Kruskal e Prim, o custo representa o peso total da Árvore Geradora Mínima. Os tempos de execução são dados em segundos.

| Grafo | Vértices | Arestas | Dijkstra (Custo) | Dijkstra (Tempo s) | Kruskal (Custo) | Kruskal (Tempo s) | Prim (Custo) | Prim (Tempo s) |
|-------|----------|---------|------------------|--------------------|-----------------|-------------------|--------------|----------------|
| USA-road-d.NY | 264346 | 733846 | 186686642878 | 0.2619 | 261159288 | 0.5365 | 261159288 | 0.3288 |
| USA-road-d.BAY | 321270 | 800172 | 295629435062 | 0.5666 | 435798417 | 0.5583 | 435798417 | 0.2482 |
| USA-road-d.COL | 435666 | 1057066 | 1104876702568 | 0.3710 | 1323900090 | 0.9259 | 1323900090 | 0.3115 |

## Conclusões

As conclusões serão elaboradas após a análise dos resultados apresentados na tabela, considerando a complexidade teórica de cada algoritmo e o desempenho observado nas instâncias de grafos reais. Será avaliado como o número de vértices e arestas impacta o tempo de execução de cada algoritmo, bem como a consistência dos custos para os algoritmos de Árvore Geradora Mínima (Kruskal e Prim).
