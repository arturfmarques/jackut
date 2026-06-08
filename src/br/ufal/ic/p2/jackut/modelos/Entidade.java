package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

/**
 * Classe abstrata base para entidades identificaveis do sistema.
 *
 * <p>Foi criada para representar o conceito comum de entidades que possuem
 * um identificador no projeto Jackut.</p>
 */
public abstract class Entidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private String id;

    /**
     * Cria uma entidade com um identificador.
     *
     * @param id identificador da entidade.
     */
    public Entidade(String id) {
        this.id = id;
    }

    /**
     * Retorna o identificador da entidade.
     *
     * @return identificador da entidade.
     */
    public String getId() {
        return id;
    }
}