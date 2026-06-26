package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando o login informado para cadastro e invalido.
 */
public class LoginInvalidoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public LoginInvalidoException() {
        super("Login inv\u00e1lido.");
    }
}
