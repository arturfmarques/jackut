package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando o usuario solicitado nao esta cadastrado.
 */
public class UsuarioNaoCadastradoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoCadastradoException() {
        super("Usu\u00e1rio n\u00e3o cadastrado.");
    }
}
