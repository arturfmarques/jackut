package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um usuario tenta ser fa de si mesmo.
 */
public class UsuarioNaoPodeSerFaDeSiMesmoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoPodeSerFaDeSiMesmoException() {
        super("Usu\u00e1rio n\u00e3o pode ser f\u00e3 de si mesmo.");
    }
}
