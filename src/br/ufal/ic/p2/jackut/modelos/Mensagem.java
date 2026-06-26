package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

/**
 * Classe abstrata que representa uma mensagem enviada dentro do Jackut.
 *
 * <p>Esta abstracao permite tratar diferentes tipos de mensagem de forma comum
 * em futuras evolucoes do projeto.</p>
 */
public abstract class Mensagem implements Serializable {

    private static final long serialVersionUID = 1L;

    private String remetente;
    private String texto;

    /**
     * Cria uma mensagem.
     *
     * @param remetente login do remetente da mensagem.
     * @param texto conteudo textual da mensagem.
     */
    public Mensagem(String remetente, String texto) {
        this.remetente = remetente;
        this.texto = texto;
    }

    /**
     * Retorna o login do remetente.
     *
     * @return login do remetente.
     */
    public String getRemetente() {
        return remetente;
    }

    /**
     * Retorna o texto da mensagem.
     *
     * @return texto da mensagem.
     */
    public String getTexto() {
        return texto;
    }
}
