package br.ufal.ic.p2.jackut.excecoes;

/**
 * Exceção personalizada do sistema Jackut.
 *
 * <p>É utilizada para representar erros de regra de negócio identificados
 * durante a execução das funcionalidades do sistema.</p>
 */
public class JackutException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    /**
     * Cria uma exceção com a mensagem informada.
     *
     * @param mensagem mensagem descritiva do erro.
     */
    public JackutException(String mensagem) {
        super(mensagem);
    }
}