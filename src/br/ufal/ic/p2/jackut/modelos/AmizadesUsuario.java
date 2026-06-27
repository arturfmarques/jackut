package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Agrupa os dados de amizade e convites de um usuario.
 */
public class AmizadesUsuario implements Serializable {

    private static final long serialVersionUID = 1L;

    private List<String> amigos;
    private List<String> convitesEnviados;

    /**
     * Cria as listas de amizades do usuario.
     */
    public AmizadesUsuario() {
        this.amigos = new ArrayList<String>();
        this.convitesEnviados = new ArrayList<String>();
    }

    /**
     * Verifica se um usuario ja e amigo.
     *
     * @param loginAmigo login do amigo.
     * @return {@code true} se for amigo; {@code false} caso contrario.
     */
    public boolean ehAmigo(String loginAmigo) {
        return amigos.contains(loginAmigo);
    }

    /**
     * Adiciona um amigo efetivado.
     *
     * @param loginAmigo login do amigo.
     */
    public void adicionarAmigoEfetivado(String loginAmigo) {
        adicionarSeAusente(amigos, loginAmigo);
    }

    /**
     * Registra um convite enviado.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void adicionarConviteEnviado(String loginConvidado) {
        adicionarSeAusente(convitesEnviados, loginConvidado);
    }

    /**
     * Remove um convite enviado.
     *
     * @param loginConvidado login do usuario convidado.
     */
    public void removerConviteEnviado(String loginConvidado) {
        convitesEnviados.remove(loginConvidado);
    }

    /**
     * Verifica se existe convite enviado para um usuario.
     *
     * @param loginConvidado login do usuario convidado.
     * @return {@code true} se existir convite pendente; {@code false} caso contrario.
     */
    public boolean possuiConviteEnviadoPara(String loginConvidado) {
        return convitesEnviados.contains(loginConvidado);
    }

    /**
     * Retorna uma copia da lista de amigos.
     *
     * @return amigos do usuario.
     */
    public List<String> getAmigos() {
        return new ArrayList<String>(amigos);
    }

    /**
     * Remove referencias a um usuario.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerReferenciasAoUsuario(String loginRemovido) {
        amigos.remove(loginRemovido);
        convitesEnviados.remove(loginRemovido);
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
