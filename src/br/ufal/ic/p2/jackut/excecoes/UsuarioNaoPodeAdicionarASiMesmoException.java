package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um usuario tenta adicionar a si mesmo como amigo.
 */
public class UsuarioNaoPodeAdicionarASiMesmoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoPodeAdicionarASiMesmoException() {
        super("Usu\u00e1rio n\u00e3o pode adicionar a si mesmo como amigo.");
    }
}
