package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando uma acao e bloqueada por regra de inimizade.
 */
public class FuncaoInvalidaException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com o nome do inimigo que bloqueou a acao.
     *
     * @param nomeInimigo nome do inimigo.
     */
    public FuncaoInvalidaException(String nomeInimigo) {
        super("Fun\u00e7\u00e3o inv\u00e1lida: " + nomeInimigo + " \u00e9 seu inimigo.");
    }
}
