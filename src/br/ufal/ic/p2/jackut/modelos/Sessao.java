package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;

import br.ufal.ic.p2.jackut.contratos.Identificavel;

/**
 * Representa uma sessao aberta por um usuario autenticado.
 */
public class Sessao implements Identificavel, Serializable {

    private static final long serialVersionUID = 1L;

    private String id;
    private String loginUsuario;

    /**
     * Cria uma nova sessao.
     *
     * @param id identificador da sessao.
     * @param loginUsuario login do usuario associado a sessao.
     */
    public Sessao(String id, String loginUsuario) {
        this.id = id;
        this.loginUsuario = loginUsuario;
    }

    /**
     * Retorna o identificador da sessao.
     *
     * @return identificador da sessao.
     */
    public String getId() {
        return id;
    }

    /**
     * Retorna o login do usuario associado a sessao.
     *
     * @return login do usuario da sessao.
     */
    public String getLoginUsuario() {
        return loginUsuario;
    }
}
