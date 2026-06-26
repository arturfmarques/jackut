package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando login ou senha nao conferem na abertura de sessao.
 */
public class LoginOuSenhaInvalidosException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public LoginOuSenhaInvalidosException() {
        super("Login ou senha inv\u00e1lidos.");
    }
}
