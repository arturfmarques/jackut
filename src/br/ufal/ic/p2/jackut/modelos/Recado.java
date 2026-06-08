package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

public class Recado implements Serializable {

    private static final long serialVersionUID = 1L;

    private String remetente;
    private String texto;

    public Recado(String remetente, String texto) {
        this.remetente = remetente;
        this.texto = texto;
    }

    public String getRemetente() {
        return remetente;
    }

    public String getTexto() {
        return texto;
    }
}