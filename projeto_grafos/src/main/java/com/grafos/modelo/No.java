package com.grafos.modelo;

import java.util.Objects;

public class No {
    private int id;

    public No(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        No no = (No) o;
        return id == no.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "No{" +
               "id=" + id +
               "}";
    }
}

