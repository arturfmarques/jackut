package br.ufal.ic.p2.jackut.excecoes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Exceção personalizada do sistema Jackut.
 *
 * <p>É utilizada para representar erros de regra de negócio identificados
 * durante a execução das funcionalidades do sistema.</p>
 *
 * <p>A mensagem é adaptada para a codificação padrão da JVM para manter
 * compatibilidade com os scripts do EasyAccept, que podem ser lidos em
 * codificações diferentes dependendo do ambiente.</p>
 */
public class JackutException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String CODIFICACAO_TESTES = "ISO-8859-1";

    /**
     * Cria uma exceção com a mensagem informada.
     *
     * @param mensagem mensagem descritiva do erro.
     */
    public JackutException(String mensagem) {
        super(ajustarMensagem(mensagem));
    }

    /**
     * Ajusta a mensagem para a codificação padrão do ambiente de execução.
     *
     * @param mensagem mensagem original do contrato dos testes.
     * @return mensagem ajustada para comparação pelo EasyAccept.
     */
    private static String ajustarMensagem(String mensagem) {
        if (mensagem == null) {
            return null;
        }

        try {
            byte[] bytesIso = mensagem.getBytes(CODIFICACAO_TESTES);
            return new String(bytesIso, Charset.defaultCharset().name());
        } catch (UnsupportedEncodingException erro) {
            return mensagem;
        }
    }
}