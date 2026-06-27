package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * Representa os dados de perfil de um usuario do Jackut.
 */
public class PerfilUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private Map<String, String> atributos;

    /**
     * Cria o perfil inicial de um usuario.
     *
     * @param nome nome publico do usuario.
     */
    public PerfilUsuario(String nome) {
        this.atributos = new HashMap<String, String>();
        this.atributos.put("nome", nome);
    }

    /**
     * Cria ou altera um atributo do perfil.
     *
     * @param atributo nome do atributo.
     * @param valor valor do atributo.
     */
    public void editar(String atributo, String valor) {
        atributos.put(atributo, valor);
    }

    /**
     * Verifica se o perfil possui um atributo.
     *
     * @param atributo nome do atributo.
     * @return {@code true} se o atributo existir; {@code false} caso contrario.
     */
    public boolean possuiAtributo(String atributo) {
        return atributos.containsKey(atributo);
    }

    /**
     * Retorna o valor de um atributo.
     *
     * @param atributo nome do atributo.
     * @return valor do atributo.
     */
    public String getAtributo(String atributo) {
        return atributos.get(atributo);
    }
}
