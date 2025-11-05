package com.grafos.util;

import com.grafos.modelo.No;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ConjuntoDisjunto {
    private Map<No, No> pai;
    private Map<No, Integer> rank;

    public ConjuntoDisjunto(List<No> nos) {
        pai = new HashMap<>();
        rank = new HashMap<>();
        for (No no : nos) {
            pai.put(no, no);
            rank.put(no, 0);
        }
    }

    public No encontrar(No i) {
        if (pai.get(i).equals(i)) {
            return i;
        }
        pai.put(i, encontrar(pai.get(i)));
        return pai.get(i);
    }

    public void unir(No i, No j) {
        No raizI = encontrar(i);
        No raizJ = encontrar(j);

        if (!raizI.equals(raizJ)) {
            if (rank.get(raizI) < rank.get(raizJ)) {
                pai.put(raizI, raizJ);
            } else if (rank.get(raizJ) < rank.get(raizI)) {
                pai.put(raizJ, raizI);
            } else {
                pai.put(raizJ, raizI);
                rank.put(raizI, rank.get(raizI) + 1);
            }
        }
    }
}

