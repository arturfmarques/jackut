package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando a senha informada para cadastro e invalida.
 */
public class SenhaInvalidaException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public SenhaInvalidaException() {
        super("Senha inv\u00e1lida.");
    }
}
