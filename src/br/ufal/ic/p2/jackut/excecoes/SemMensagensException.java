package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando nao ha mensagens pendentes.
 */
public class SemMensagensException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public SemMensagensException() {
        super("N\u00e3o h\u00e1 mensagens.");
    }
}
