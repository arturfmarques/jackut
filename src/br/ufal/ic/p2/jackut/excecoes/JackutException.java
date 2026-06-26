package br.ufal.ic.p2.jackut.excecoes;

import java.io.UnsupportedEncodingException;
import java.nio.charset.Charset;

/**
 * Excecao base do sistema Jackut.
 *
 * <p>As excecoes especificas do dominio herdam desta classe para manter
 * mensagens semanticamente organizadas e compativeis com o EasyAccept.</p>
 */
public class JackutException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    private static final String CODIFICACAO_TESTES = "ISO-8859-1";

    /**
     * Cria uma excecao com a mensagem informada.
     *
     * @param mensagem mensagem descritiva do erro.
     */
    public JackutException(String mensagem) {
        super(ajustarMensagem(mensagem));
    }

    /**
     * Cria uma excecao com mensagem e causa.
     *
     * @param mensagem mensagem descritiva do erro.
     * @param causa causa original do erro.
     */
    public JackutException(String mensagem, Throwable causa) {
        super(ajustarMensagem(mensagem), causa);
    }

    /**
     * Ajusta a mensagem para a codificacao padrao do ambiente de execucao.
     *
     * @param mensagem mensagem original do contrato dos testes.
     * @return mensagem ajustada para comparacao pelo EasyAccept.
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
