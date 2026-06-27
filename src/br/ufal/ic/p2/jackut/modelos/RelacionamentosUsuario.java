package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Agrupa os relacionamentos sociais nao baseados em amizade.
 */
public class RelacionamentosUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> idolos;
    private List<String> fas;
    private List<String> paqueras;
    private List<String> inimigos;

    /**
     * Cria as listas de relacionamentos do usuario.
     */
    public RelacionamentosUsuario() {
        this.idolos = new ArrayList<String>();
        this.fas = new ArrayList<String>();
        this.paqueras = new ArrayList<String>();
        this.inimigos = new ArrayList<String>();
    }

    /**
     * Adiciona um idolo.
     *
     * @param loginIdolo login do idolo.
     */
    public void adicionarIdolo(String loginIdolo) {
        adicionarSeAusente(idolos, loginIdolo);
    }

    /**
     * Verifica se o usuario e fa de outro.
     *
     * @param loginIdolo login do idolo.
     * @return {@code true} se for fa; {@code false} caso contrario.
     */
    public boolean ehFaDe(String loginIdolo) {
        return idolos.contains(loginIdolo);
    }

    /**
     * Adiciona um fa.
     *
     * @param loginFa login do fa.
     */
    public void adicionarFa(String loginFa) {
        adicionarSeAusente(fas, loginFa);
    }

    /**
     * Retorna os fas do usuario.
     *
     * @return copia da lista de fas.
     */
    public List<String> getFas() {
        return new ArrayList<String>(fas);
    }

    /**
     * Adiciona uma paquera.
     *
     * @param loginPaquera login da paquera.
     */
    public void adicionarPaquera(String loginPaquera) {
        adicionarSeAusente(paqueras, loginPaquera);
    }

    /**
     * Verifica se o usuario tem uma paquera.
     *
     * @param loginPaquera login da paquera.
     * @return {@code true} se for paquera; {@code false} caso contrario.
     */
    public boolean ehPaqueraDe(String loginPaquera) {
        return paqueras.contains(loginPaquera);
    }

    /**
     * Retorna as paqueras do usuario.
     *
     * @return copia da lista de paqueras.
     */
    public List<String> getPaqueras() {
        return new ArrayList<String>(paqueras);
    }

    /**
     * Adiciona um inimigo.
     *
     * @param loginInimigo login do inimigo.
     */
    public void adicionarInimigo(String loginInimigo) {
        adicionarSeAusente(inimigos, loginInimigo);
    }

    /**
     * Verifica se o usuario possui um inimigo.
     *
     * @param loginInimigo login do inimigo.
     * @return {@code true} se for inimigo; {@code false} caso contrario.
     */
    public boolean possuiInimigo(String loginInimigo) {
        return inimigos.contains(loginInimigo);
    }

    /**
     * Remove referencias a um usuario.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerReferenciasAoUsuario(String loginRemovido) {
        idolos.remove(loginRemovido);
        fas.remove(loginRemovido);
        paqueras.remove(loginRemovido);
        inimigos.remove(loginRemovido);
    }

    /**
     * Adiciona um item a uma lista caso ele ainda nao exista.
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
