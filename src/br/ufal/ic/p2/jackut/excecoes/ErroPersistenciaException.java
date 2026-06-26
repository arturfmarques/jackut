package br.ufal.ic.p2.jackut.excecoes;

/**
 * Excecao lancada quando ocorre falha tecnica de persistencia.
 */
public class ErroPersistenciaException extends JackutException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria uma excecao de persistencia.
     *
     * @param mensagem mensagem descritiva do erro.
     * @param causa causa original do erro.
     */
    public ErroPersistenciaException(String mensagem, Throwable causa) {
        super(mensagem, causa);
    }
}
