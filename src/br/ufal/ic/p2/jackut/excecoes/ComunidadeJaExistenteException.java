package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando ja existe comunidade com o nome informado.
 */
public class ComunidadeJaExistenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public ComunidadeJaExistenteException() {
        super("Comunidade com esse nome j\u00e1 existe.");
    }
}
