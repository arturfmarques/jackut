package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Representa uma comunidade do Jackut.
 */
public class Comunidade implements Serializable {

    private static final long serialVersionUID = 1L;

    private String nome;
    private String descricao;
    private String dono;
    private List<String> membros;

    /**
     * Cria uma comunidade.
     *
     * @param nome nome da comunidade.
     * @param descricao descricao da comunidade.
     * @param dono login do dono.
     */
    public Comunidade(String nome, String descricao, String dono) {
        this.nome = nome;
        this.descricao = descricao;
        this.dono = dono;
        this.membros = new ArrayList<String>();
        this.membros.add(dono);
    }

    /**
     * Retorna o nome da comunidade.
     *
     * @return nome da comunidade.
     */
    public String getNome() {
        return nome;
    }

    /**
     * Retorna a descricao da comunidade.
     *
     * @return descricao da comunidade.
     */
    public String getDescricao() {
        return descricao;
    }

    /**
     * Retorna o dono da comunidade.
     *
     * @return login do dono.
     */
    public String getDono() {
        return dono;
    }

    /**
     * Adiciona um membro a comunidade.
     *
     * @param login login do usuario.
     */
    public void adicionarMembro(String login) {
        if (!membros.contains(login)) {
            membros.add(login);
        }
    }

    /**
     * Remove um membro da comunidade.
     *
     * @param login login do usuario.
     */
    public void removerMembro(String login) {
        membros.remove(login);
    }

    /**
     * Verifica se o usuario e membro da comunidade.
     *
     * @param login login do usuario.
     * @return {@code true} se for membro; {@code false} caso contrario.
     */
    public boolean possuiMembro(String login) {
        return membros.contains(login);
    }

    /**
     * Retorna uma copia da lista de membros.
     *
     * @return lista de membros.
     */
    public List<String> getMembros() {
        return new ArrayList<String>(membros);
    }
}
