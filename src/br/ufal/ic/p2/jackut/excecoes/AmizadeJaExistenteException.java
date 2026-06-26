package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando uma amizade ja esta efetivada.
 */
public class AmizadeJaExistenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public AmizadeJaExistenteException() {
        super("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo.");
    }
}
