package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

public abstract class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    public Entidade(String id) {
        this.id = id;
    }

    public String getId() {
        return id;
    }
}