package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Agrupa as comunidades das quais um usuario participa.
 */
public class ParticipacoesUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> comunidades;

    /**
     * Cria a lista de comunidades do usuario.
     */
    public ParticipacoesUsuario() {
        this.comunidades = new ArrayList<String>();
    }

    /**
     * Adiciona uma comunidade ao usuario.
     *
     * @param nomeComunidade nome da comunidade.
     */
    public void adicionarComunidade(String nomeComunidade) {
        adicionarSeAusente(comunidades, nomeComunidade);
    }

    /**
     * Remove uma comunidade do usuario.
     *
     * @param nomeComunidade nome da comunidade.
     */
    public void removerComunidade(String nomeComunidade) {
        comunidades.remove(nomeComunidade);
    }

    /**
     * Verifica se o usuario participa de uma comunidade.
     *
     * @param nomeComunidade nome da comunidade.
     * @return {@code true} se participa; {@code false} caso contrario.
     */
    public boolean participaDaComunidade(String nomeComunidade) {
        return comunidades.contains(nomeComunidade);
    }

    /**
     * Retorna uma copia das comunidades do usuario.
     *
     * @return comunidades do usuario.
     */
    public List<String> getComunidades() {
        return new ArrayList<String>(comunidades);
    }

    /**
     * Adiciona um item a lista caso ele ainda nao exista.
     *
     * @param lista lista modificada.
     * @param item item adicionado.
     */
    private void adicionarSeAusente(List<String> lista, String item) {
        if (!lista.contains(item)) {
            lista.add(item);
        }
    }
}
