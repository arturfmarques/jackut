package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um usuario tenta ser paquera de si mesmo.
 */
public class UsuarioNaoPodeSerPaqueraDeSiMesmoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoPodeSerPaqueraDeSiMesmoException() {
        super("Usu\u00e1rio n\u00e3o pode ser paquera de si mesmo.");
    }
}
