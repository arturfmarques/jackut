package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando nao existem recados pendentes.
 */
public class SemRecadosException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public SemRecadosException() {
        super("N\u00e3o h\u00e1 recados.");
    }
}
