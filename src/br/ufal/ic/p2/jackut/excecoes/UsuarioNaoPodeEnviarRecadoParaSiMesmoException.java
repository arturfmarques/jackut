package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando um usuario tenta enviar recado para si mesmo.
 */
public class UsuarioNaoPodeEnviarRecadoParaSiMesmoException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria a excecao com a mensagem esperada pelos testes.
     */
    public UsuarioNaoPodeEnviarRecadoParaSiMesmoException() {
        super("Usu\u00e1rio n\u00e3o pode enviar recado para si mesmo.");
    }
}
