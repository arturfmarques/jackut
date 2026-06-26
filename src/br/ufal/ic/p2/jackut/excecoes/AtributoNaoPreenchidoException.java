package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um atributo de perfil nao foi preenchido.
 */
public class AtributoNaoPreenchidoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public AtributoNaoPreenchidoException() {
        super("Atributo n\u00e3o preenchido.");
    }
}
