package br.ufal.ic.p2.jackut.modelos;

import java.io.Serializable;
import java.util.LinkedList;

/**
 * Representa a caixa de entrada de recados e mensagens de um usuario.
 */
public class CaixaEntrada implements Serializable {

    private static final long serialVersionUID = 1L;

    private LinkedList<Recado> recados;
    private LinkedList<MensagemComunidade> mensagens;

    /**
     * Cria uma caixa de entrada vazia.
     */
    public CaixaEntrada() {
        this.recados = new LinkedList<Recado>();
        this.mensagens = new LinkedList<MensagemComunidade>();
    }

    /**
     * Recebe um recado.
     *
     * @param recado recado recebido.
     */
    public void receberRecado(Recado recado) {
        recados.addLast(recado);
    }

    /**
     * Verifica se existem recados pendentes.
     *
     * @return {@code true} se houver recados; {@code false} caso contrario.
     */
    public boolean possuiRecados() {
        return !recados.isEmpty();
    }

    /**
     * Remove o primeiro recado pendente.
     *
     * @return primeiro recado da fila.
     */
    public Recado removerPrimeiroRecado() {
        return recados.removeFirst();
    }

    /**
     * Recebe uma mensagem de comunidade.
     *
     * @param mensagem mensagem recebida.
     */
    public void receberMensagem(MensagemComunidade mensagem) {
        mensagens.addLast(mensagem);
    }

    /**
     * Verifica se existem mensagens pendentes.
     *
     * @return {@code true} se houver mensagens; {@code false} caso contrario.
     */
    public boolean possuiMensagens() {
        return !mensagens.isEmpty();
    }

    /**
     * Remove a primeira mensagem pendente.
     *
     * @return primeira mensagem da fila.
     */
    public MensagemComunidade removerPrimeiraMensagem() {
        return mensagens.removeFirst();
    }

    /**
     * Remove recados enviados por um usuario removido.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerRecadosDoRemetente(String loginRemovido) {
        LinkedList<Recado> mantidos = new LinkedList<Recado>();

        for (Recado recado : recados) {
            if (!loginRemovido.equals(recado.getRemetente())) {
                mantidos.add(recado);
            }
        }

        recados = mantidos;
    }

    /**
     * Remove mensagens enviadas por um usuario removido.
     *
     * @param loginRemovido login removido do sistema.
     */
    public void removerMensagensDoRemetente(String loginRemovido) {
        LinkedList<MensagemComunidade> mantidas = new LinkedList<MensagemComunidade>();

        for (MensagemComunidade mensagem : mensagens) {
            if (!loginRemovido.equals(mensagem.getRemetente())) {
                mantidas.add(mensagem);
            }
        }

        mensagens = mantidas;
    }
}
