package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um usuario tenta ser inimigo de si mesmo.
 */
public class UsuarioNaoPodeSerInimigoDeSiMesmoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoPodeSerInimigoDeSiMesmoException() {
        super("Usu\u00e1rio n\u00e3o pode ser inimigo de si mesmo.");
    }
}
