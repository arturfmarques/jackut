package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando ja existe convite de amizade pendente.
 */
public class ConviteAmizadePendenteException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public ConviteAmizadePendenteException() {
        super("Usu\u00e1rio j\u00e1 est\u00e1 adicionado como amigo, esperando aceita\u00e7\u00e3o do convite.");
    }
}
