package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando a comunidade solicitada nao existe.
 */
public class ComunidadeNaoExisteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public ComunidadeNaoExisteException() {
        super("Comunidade n\u00e3o existe.");
    }
}
