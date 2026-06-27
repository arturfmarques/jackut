package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando o idolo ja foi adicionado.
 */
public class IdoloJaExistenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public IdoloJaExistenteException() {
        super("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como \u00eddolo.");
    }
}
