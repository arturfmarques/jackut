package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando a paquera ja foi adicionada.
 */
public class PaqueraJaExistenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public PaqueraJaExistenteException() {
        super("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como paquera.");
    }
}
