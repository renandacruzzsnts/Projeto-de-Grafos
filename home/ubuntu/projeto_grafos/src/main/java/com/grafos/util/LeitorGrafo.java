package com.grafos.util;

import com.grafos.modelo.Grafo;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LeitorGrafo {

    public static Grafo lerGrafo(String caminhoArquivo) throws IOException {
        BufferedReader leitor = new BufferedReader(new FileReader(caminhoArquivo));
        String linha;
        int numNos = 0;
        int numArestas = 0;
        Grafo grafo = null;

        while ((linha = leitor.readLine()) != null) {
            linha = linha.trim();
            if (linha.isEmpty() || linha.startsWith("c")) {
                continue; // Pula linhas vazias e comentários
            }

            String[] partes = linha.split("\\s+");

            if (partes[0].equals("p")) {
                if (partes.length >= 4 && partes[1].equals("sp")) {
                    numNos = Integer.parseInt(partes[2]);
                    numArestas = Integer.parseInt(partes[3]);
                    grafo = new Grafo(numNos, numArestas);
                } else {
                    leitor.close();
                    throw new IOException("Formato inválido na linha do problema: " + linha);
                }
            } else if (partes[0].equals("a")) {
                if (grafo == null) {
                    leitor.close();
                    throw new IOException("Linha do problema não encontrada antes dos descritores de aresta.");
                }
                if (partes.length >= 4) {
                    int origem = Integer.parseInt(partes[1]);
                    int destino = Integer.parseInt(partes[2]);
                    int peso = Integer.parseInt(partes[3]);
                    grafo.adicionarAresta(origem, destino, peso);
                } else {
                    leitor.close();
                    throw new IOException("Formato inválido na linha do descritor de aresta: " + linha);
                }
            }
        }
        leitor.close();
        return grafo;
    }
}

