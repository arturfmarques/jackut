package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando ja existe conta com o login informado.
 */
public class ContaJaExistenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public ContaJaExistenteException() {
        super("Conta com esse nome j\u00e1 existe.");
    }
}
