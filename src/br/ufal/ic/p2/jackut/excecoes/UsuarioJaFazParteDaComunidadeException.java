package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando o usuario ja participa da comunidade.
 */
public class UsuarioJaFazParteDaComunidadeException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioJaFazParteDaComunidadeException() {
        super("Usuario j\u00e1 faz parte dessa comunidade.");
    }
}
